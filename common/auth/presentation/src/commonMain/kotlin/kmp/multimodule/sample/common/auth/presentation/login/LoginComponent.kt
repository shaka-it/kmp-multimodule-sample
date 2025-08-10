package kmp.multimodule.sample.common.auth.presentation.login

import com.arkivanov.decompose.value.Value

interface LoginComponent {
    val viewState: Value<ViewState>

    fun onLoginChanged(value: String)
    fun onPasswordChanged(value: String)
    fun onPasswordShowClick()
    fun onLoginClick()
    fun onRegisterClick()
    fun onForgotClick()

    data class ViewState(
        val login: String = "",
        val password: String = "",
        val isSending: Boolean = false,
        val isPasswordHidden: Boolean = true
    )

    sealed interface NavEvent {
        object OpenMainFlow : NavEvent

        object OpenRegister : NavEvent

        object OpenForgotPassword : NavEvent
    }
}