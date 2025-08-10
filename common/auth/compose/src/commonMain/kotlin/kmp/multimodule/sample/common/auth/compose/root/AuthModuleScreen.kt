package kmp.multimodule.sample.common.auth.compose.root

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import kmp.multimodule.sample.common.auth.compose.forgot.ForgotPasswordScreen
import kmp.multimodule.sample.common.auth.compose.login.LoginScreen
import kmp.multimodule.sample.common.auth.compose.register.RegisterScreen
import kmp.multimodule.sample.common.auth.presentation.flow.AuthFlowComponent
import kmp.multimodule.sample.common.auth.presentation.flow.FakeAuthFlowComponent
import kmp.multimodule.sample.common.core.compose.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun AuthModuleScreen(
    component: AuthFlowComponent,
    modifier: Modifier = Modifier,
) {
    val childStack by component.childStack.subscribeAsState()

    Children(
        stack = childStack,
        modifier = modifier,
        animation = stackAnimation(slide()),
    ) { child ->
        when (val instance = child.instance) {
            is AuthFlowComponent.Child.Login -> LoginScreen(instance.component)
            is AuthFlowComponent.Child.Register -> RegisterScreen(instance.component)
            is AuthFlowComponent.Child.ForgotPassword -> ForgotPasswordScreen(instance.component)
        }
    }
}

@Preview
@Composable
private fun AuthModuleScreenPreview() {
    AppTheme {
        AuthModuleScreen(FakeAuthFlowComponent())
    }
}
