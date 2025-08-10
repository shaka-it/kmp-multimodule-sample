package kmp.multimodule.sample.common.core.compose.components

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kmp.multimodule.sample.common.core.compose.theme.Theme.colors

@Composable
fun ThemedTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(text, color = colors.highlightTextColor)
    }
}