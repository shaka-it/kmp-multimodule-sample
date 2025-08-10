package kmp.multimodule.sample.common.auth.presentation.register

interface RegisterComponent {

    fun onBackClick()

    sealed interface NavEvent {
        object Back : NavEvent
    }
}