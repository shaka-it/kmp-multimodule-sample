package kmp.multimodule.sample.common.core.compose.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import kmp.multimodule.sample.common.core.compose.theme.Theme.colors

@Composable
fun ThemedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    imeAction: ImeAction = ImeAction.Next,
    textStyle: TextStyle = TextStyle(fontSize = 16.sp),
    isPassword: Boolean = false,
    isHidden: Boolean = true,
    onToggleHidden: (() -> Unit)? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, color = colors.secondaryTextColor) },
        textStyle = textStyle.copy(color = colors.primaryTextColor),
        singleLine = singleLine,
        modifier = modifier,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = imeAction),
        visualTransformation = if (isPassword && isHidden) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = if (isPassword && onToggleHidden != null) {
            {
                val icon = if (isHidden) Icons.Default.Visibility else Icons.Default.VisibilityOff
                IconButton(onClick = onToggleHidden) {
                    Icon(imageVector = icon, contentDescription = null, tint = colors.highlightTextColor)
                }
            }
        } else null,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = colors.primaryAction,
            cursorColor = colors.primaryAction,
            unfocusedBorderColor = colors.secondaryTextColor
        )
    )
}