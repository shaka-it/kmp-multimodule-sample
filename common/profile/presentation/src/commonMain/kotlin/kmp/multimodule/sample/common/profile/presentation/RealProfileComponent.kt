package kmp.multimodule.sample.common.profile.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import kmp.multimodule.sample.common.auth.api.AuthRepository
import kmp.multimodule.sample.common.core.presentation.utils.Consumer
import kmp.multimodule.sample.common.core.presentation.utils.invoke

class RealProfileComponent(
    componentContext: ComponentContext,
    private val onNavEvent: Consumer<ProfileComponent.NavEvent>,
    private val authRepository: AuthRepository,
) : ProfileComponent, ComponentContext by componentContext {

    override val viewState: Value<ProfileComponent.ViewState> =
        MutableValue(
            ProfileComponent.ViewState(
                login = authRepository.getLogin(),
                imageUrl = "https://i.pinimg.com/736x/57/00/c0/5700c04197ee9a4372a35ef16eb78f4e.jpg",
            )
        )

    override fun onLogoutClicked() {
        authRepository.logout()
        onNavEvent(ProfileComponent.NavEvent.OpenAuthFlow)
    }
}