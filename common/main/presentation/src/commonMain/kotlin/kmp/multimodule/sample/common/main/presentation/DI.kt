package kmp.multimodule.sample.common.main.presentation

import com.arkivanov.decompose.ComponentContext
import kmp.multimodule.sample.common.core.presentation.component.ComponentFactory
import kmp.multimodule.sample.common.core.presentation.utils.Consumer
import kmp.multimodule.sample.common.main.presentation.flow.MainFlowComponent
import kmp.multimodule.sample.common.main.presentation.flow.MainFlowComponent.NavEvent
import kmp.multimodule.sample.common.main.presentation.flow.RealMainFlowComponent
import org.koin.core.component.get

fun ComponentFactory.createMainModuleComponent(
    componentContext: ComponentContext,
    onNavEvent: Consumer<NavEvent>,
): MainFlowComponent {
    return RealMainFlowComponent(
        componentContext = componentContext,
        onNavEvent = onNavEvent,
        componentFactory = get(),
    )
}