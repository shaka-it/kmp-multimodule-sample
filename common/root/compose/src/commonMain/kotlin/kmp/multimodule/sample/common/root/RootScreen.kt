package kmp.multimodule.sample.common.root

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import kmp.multimodule.sample.common.auth.compose.root.AuthModuleScreen
import kmp.multimodule.sample.common.core.compose.desktop.LocalAppSettings
import kmp.multimodule.sample.common.core.compose.desktop.WindowState
import kmp.multimodule.sample.common.main.compose.MainModuleScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import kmp.multimodule.sample.common.core.compose.theme.AppTheme

@Composable
fun RootScreen(
    component: RootComponent,
    modifier: Modifier = Modifier,
) {
    val childStack by component.childStack.subscribeAsState()
    val appSettings = LocalAppSettings.current

    Children(
        stack = childStack,
        modifier = modifier,
        animation = stackAnimation(slide()),
    ) { child ->
        when (val instance = child.instance) {
            is RootComponent.Child.AuthModule -> AuthModuleScreen(instance.component)
            is RootComponent.Child.MainModule -> {
                MainModuleScreen(instance.component)
                appSettings.updateWindowState(WindowState.FullScreen)
            }
        }
    }
}

@Preview
@Composable
private fun RootScreenPreview() {
    AppTheme {
        RootScreen(FakeRootComponent())
    }
}
