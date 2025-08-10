package kmp.multimodule.sample.common.auth.compose.register

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import kmp.multimodule.sample.common.auth.presentation.register.FakeRegisterComponent
import kmp.multimodule.sample.common.auth.presentation.register.RegisterComponent
import kmp.multimodule.sample.common.core.compose.components.AppToolbar
import kmp.multimodule.sample.common.core.compose.theme.AppTheme
import kmp.multimodule.sample.common.core.compose.theme.Theme.colors
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun RegisterScreen(
    component: RegisterComponent,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            AppToolbar(
                title = "Register",
                onBackClick = component::onBackClick,
            )
        },
        containerColor = colors.primaryBackground
    ) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "This is the register screen",
                fontSize = 18.sp,
                color = colors.primaryTextColor
            )
        }
    }
}

@Preview
@Composable
private fun RegisterScreenPreview() {
    AppTheme {
        RegisterScreen(FakeRegisterComponent())
    }
}
