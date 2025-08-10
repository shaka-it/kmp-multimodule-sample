package kmp.multimodule.sample.common.auth.presentation.forgot

import com.arkivanov.decompose.ComponentContext
import kmp.multimodule.sample.common.auth.presentation.forgot.ForgotPasswordComponent.NavEvent
import kmp.multimodule.sample.common.core.presentation.utils.Consumer
import kmp.multimodule.sample.common.core.presentation.utils.invoke

internal class RealForgotPasswordComponent(
    componentContext: ComponentContext,
    private val onNavEvent: Consumer<NavEvent>,
): ForgotPasswordComponent, ComponentContext by componentContext {
    override fun onBackClick() {
        onNavEvent(NavEvent.Back)
    }
}