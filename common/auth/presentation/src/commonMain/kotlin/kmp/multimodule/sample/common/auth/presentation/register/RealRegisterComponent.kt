package kmp.multimodule.sample.common.auth.presentation.register

import com.arkivanov.decompose.ComponentContext
import kmp.multimodule.sample.common.auth.presentation.register.RegisterComponent.NavEvent
import kmp.multimodule.sample.common.core.presentation.utils.Consumer
import kmp.multimodule.sample.common.core.presentation.utils.invoke

internal class RealRegisterComponent(
    componentContext: ComponentContext,
    private val onNavEvent: Consumer<NavEvent>,
): RegisterComponent, ComponentContext by componentContext {
    override fun onBackClick() {
        onNavEvent(NavEvent.Back)
    }
}