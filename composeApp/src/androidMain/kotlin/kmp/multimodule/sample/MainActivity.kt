package kmp.multimodule.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.defaultComponentContext
import kmp.multimodule.sample.common.core.compose.theme.AppTheme
import kmp.multimodule.sample.common.core.di.Inject
import kmp.multimodule.sample.common.core.presentation.component.ComponentFactory
import kmp.multimodule.sample.common.root.RootScreen
import kmp.multimodule.sample.common.root.createRootComponent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val componentFactory = Inject.instance<ComponentFactory>()
        val rootComponent = componentFactory.createRootComponent(defaultComponentContext())

        setContent {
            AppTheme {
                RootScreen(component = rootComponent)
            }
        }
    }
}