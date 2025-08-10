package kmp.multimodule.sample.common.auth.compose.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import kmp.multimodule.sample.common.auth.presentation.login.FakeLoginComponent
import kmp.multimodule.sample.common.auth.presentation.login.LoginComponent
import kmp.multimodule.sample.common.core.compose.components.ThemedButton
import kmp.multimodule.sample.common.core.compose.components.ThemedTextButton
import kmp.multimodule.sample.common.core.compose.components.ThemedTextField
import kmp.multimodule.sample.common.core.compose.theme.AppTheme
import kmp.multimodule.sample.common.core.compose.theme.Theme.colors
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun LoginScreen(
    component: LoginComponent,
    modifier: Modifier = Modifier
) {
    val state by component.viewState.subscribeAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.primaryBackground)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Login",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = colors.primaryTextColor
        )
        Spacer(modifier = Modifier.height(24.dp))

        ThemedTextField(
            value = state.login,
            onValueChange = component::onLoginChanged,
            label = "Login",
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        ThemedTextField(
            value = state.password,
            onValueChange = component::onPasswordChanged,
            label = "Password",
            modifier = Modifier.fillMaxWidth(),
            imeAction = ImeAction.Done,
            isPassword = true,
            isHidden = state.isPasswordHidden,
            onToggleHidden = component::onPasswordShowClick
        )

        Spacer(modifier = Modifier.height(24.dp))

        ThemedButton(
            text = if (state.isSending) "Logging in…" else "Login",
            onClick = component::onLoginClick,
            modifier = Modifier.fillMaxWidth().height(48.dp),
            enabled = !state.isSending,
            loading = state.isSending
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ThemedTextButton(text = "Forgot Password?", onClick = component::onForgotClick)
            ThemedTextButton(text = "Register", onClick = component::onRegisterClick)
        }
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    AppTheme {
        LoginScreen(FakeLoginComponent())
    }
}
