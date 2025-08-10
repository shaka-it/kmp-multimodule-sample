package kmp.multimodule.sample

import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.ApplicationLifecycle
import kmp.multimodule.sample.common.core.PlatformConfiguration
import kmp.multimodule.sample.common.core.presentation.component.ComponentFactory
import kmp.multimodule.sample.common.core.di.Inject
import kmp.multimodule.sample.common.root.RootScreen
import kmp.multimodule.sample.common.root.createRootComponent
import kmp.multimodule.sample.common.umbrella.core.PlatformSDK
import kmp.multimodule.sample.common.core.compose.theme.AppTheme

fun MainViewController() = ComposeUIViewController {
    PlatformSDK.init(configuration = PlatformConfiguration())

    val componentContext = DefaultComponentContext(lifecycle = ApplicationLifecycle())
    val componentFactory = Inject.instance<ComponentFactory>()
    val rootComponent = componentFactory.createRootComponent(componentContext)

    AppTheme {
        RootScreen(component = rootComponent)
    }
}