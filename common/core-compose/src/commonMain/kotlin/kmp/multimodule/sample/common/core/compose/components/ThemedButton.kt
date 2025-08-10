package kmp.multimodule.sample.common.core.compose.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kmp.multimodule.sample.common.core.compose.theme.Theme.colors

@Composable
fun ThemedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = colors.primaryAction,
            contentColor = colors.primaryTextColor
        )
    ) {
        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp),
                strokeWidth = 2.dp,
                color = colors.primaryTextColor
            )
        } else {
            Text(text)
        }
    }
}