package kmp.multimodule.sample.common.auth.presentation.forgot

interface ForgotPasswordComponent {

    fun onBackClick()

    sealed interface NavEvent {
        data object Back : NavEvent
    }
}