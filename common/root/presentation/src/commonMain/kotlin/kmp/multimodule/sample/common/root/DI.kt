package kmp.multimodule.sample.common.root

import com.arkivanov.decompose.ComponentContext
import kmp.multimodule.sample.common.core.presentation.component.ComponentFactory
import org.koin.core.component.get

fun ComponentFactory.createRootComponent(componentContext: ComponentContext): RootComponent {
    return RealRootComponent(
        componentContext = componentContext,
        componentFactory = get(),
        authRepository = get(),
    )
}