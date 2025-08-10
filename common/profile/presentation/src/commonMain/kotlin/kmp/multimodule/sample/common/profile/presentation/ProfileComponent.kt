package kmp.multimodule.sample.common.profile.presentation

import com.arkivanov.decompose.value.Value

interface ProfileComponent {
    val viewState: Value<ViewState>

    fun onLogoutClicked()

    data class ViewState(
        val login: String = "",
        val imageUrl: String = "",
    )

    sealed interface NavEvent {
        data object OpenAuthFlow : NavEvent
    }
}