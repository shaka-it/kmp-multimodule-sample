package kmp.multimodule.sample.common.core.compose.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class AppColors(
    val primaryBackground: Color,
    val primaryAction: Color,
    val primaryTextColor: Color,
    val hintTextColor: Color,
    val highlightTextColor: Color,
    val secondaryTextColor: Color,
    val thirdTextColor: Color,
    val tagColor: Color,
    val tagTextColor: Color
)

val palette = AppColors(
    primaryBackground   = Color(0xFFFFFFFF),
    primaryAction       = Color(0xFF2196F3),
    primaryTextColor    = Color(0xFF000000),
    hintTextColor       = Color(0xFF888888),
    highlightTextColor  = Color(0xFF2196F3),
    secondaryTextColor  = Color(0xFFCCCCCC),
    thirdTextColor      = Color(0xFF666666),
    tagColor            = Color(0x332196F3),
    tagTextColor        = Color(0xFF2196F3)
)

val LocalColorProvider = staticCompositionLocalOf { palette }