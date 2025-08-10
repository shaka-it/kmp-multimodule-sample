package kmp.multimodule.sample

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import kmp.multimodule.sample.common.core.PlatformConfiguration
import kmp.multimodule.sample.common.core.compose.theme.AppTheme
import kmp.multimodule.sample.common.core.di.Inject
import kmp.multimodule.sample.common.core.presentation.component.ComponentFactory
import kmp.multimodule.sample.common.root.RootScreen
import kmp.multimodule.sample.common.root.createRootComponent
import kmp.multimodule.sample.common.umbrella.core.PlatformSDK
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        PlatformSDK.init(
            configuration = PlatformConfiguration()
        )

        val lifecycle = LifecycleRegistry()
        val componentContext = DefaultComponentContext(lifecycle = lifecycle)
        val componentFactory = Inject.instance<ComponentFactory>()
        val rootComponent = componentFactory.createRootComponent(componentContext)

        AppTheme {
            RootScreen(component = rootComponent)
        }
    }
}