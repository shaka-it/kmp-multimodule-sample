package kmp.multimodule.sample

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import kmp.multimodule.sample.common.core.PlatformConfiguration
import kmp.multimodule.sample.common.core.compose.desktop.AppSettings
import kmp.multimodule.sample.common.core.compose.desktop.LocalAppSettings
import kmp.multimodule.sample.common.core.compose.desktop.WindowState
import kmp.multimodule.sample.common.core.compose.theme.AppTheme
import kmp.multimodule.sample.common.core.di.Inject
import kmp.multimodule.sample.common.core.presentation.component.ComponentFactory
import kmp.multimodule.sample.common.root.RootScreen
import kmp.multimodule.sample.common.root.createRootComponent
import kmp.multimodule.sample.common.umbrella.core.PlatformSDK

fun main() = application {
    PlatformSDK.init(
        configuration = PlatformConfiguration()
    )

    val appSettings = remember { AppSettings() }
    val windowState by appSettings.windowState.collectAsState()

    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        size = DpSize(1024.dp, 800.dp),
        position = WindowPosition.Aligned(Alignment.Center)
    )

    val lifecycle = LifecycleRegistry()
    val componentContext = DefaultComponentContext(lifecycle = lifecycle)
    val componentFactory = Inject.instance<ComponentFactory>()
    val rootComponent = componentFactory.createRootComponent(componentContext)

    Window(
        onCloseRequest = ::exitApplication,
        title = "kmp-multimodule-sample",
        state = state,
    ) {
        when (val ws = windowState) {
            WindowState.Maximized -> state.placement = WindowPlacement.Maximized
            WindowState.FullScreen -> state.placement = WindowPlacement.Fullscreen
            is WindowState.Custom -> {
                state.placement = WindowPlacement.Floating
                state.size = DpSize(ws.width, ws.height)
            }
        }
        AppTheme {
            CompositionLocalProvider(LocalAppSettings provides appSettings) {
                RootScreen(component = rootComponent)
            }
        }
    }
}