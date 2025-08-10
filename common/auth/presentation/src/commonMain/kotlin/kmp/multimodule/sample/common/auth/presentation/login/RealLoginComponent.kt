package kmp.multimodule.sample.common.auth.presentation.login

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import kmp.multimodule.sample.common.auth.api.AuthRepository
import kmp.multimodule.sample.common.auth.presentation.login.LoginComponent.ViewState
import kmp.multimodule.sample.common.core.presentation.utils.Consumer
import kmp.multimodule.sample.common.core.presentation.utils.invoke
import kmp.multimodule.sample.common.core.presentation.utils.tryToUpdate
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

internal class RealLoginComponent(
    componentContext: ComponentContext,
    private val onNavEvent: Consumer<LoginComponent.NavEvent>,
    private val authRepository: AuthRepository,
    mainContext: CoroutineContext,
) : LoginComponent, ComponentContext by componentContext {

    private val scope = coroutineScope(mainContext + SupervisorJob())
    override val viewState: Value<ViewState> = MutableValue(ViewState())

    override fun onLoginChanged(value: String) {
        viewState.tryToUpdate {
            it.copy(login = value)
        }
    }

    override fun onPasswordChanged(value: String) {
        viewState.tryToUpdate {
            it.copy(password = value)
        }
    }

    override fun onPasswordShowClick() {
        viewState.tryToUpdate {
            it.copy(isPasswordHidden = !it.isPasswordHidden)
        }
    }

    override fun onLoginClick() {
        viewState.tryToUpdate {
            it.copy(isSending = true)
        }
        scope.launch {
            try {
                val response = authRepository.login(
                    login = viewState.value.login,
                    password = viewState.value.password,
                )
                if (response.token.isNotBlank()) {
                    viewState.tryToUpdate {
                        it.copy(login = "", password = "", isSending = false)
                    }
                    onNavEvent(LoginComponent.NavEvent.OpenMainFlow)
                } else {
                    viewState.tryToUpdate {
                        it.copy(isSending = false)
                    }
                }
            } catch (_: Throwable) {
                viewState.tryToUpdate {
                    it.copy(isSending = false)
                }
            }
        }
    }

    override fun onRegisterClick() {
        onNavEvent(LoginComponent.NavEvent.OpenRegister)
    }

    override fun onForgotClick() {
        onNavEvent(LoginComponent.NavEvent.OpenForgotPassword)
    }
}