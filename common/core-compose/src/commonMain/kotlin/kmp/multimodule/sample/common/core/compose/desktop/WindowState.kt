package kmp.multimodule.sample.common.core.compose.desktop

import androidx.compose.ui.unit.Dp

sealed class WindowState {
    object Maximized : WindowState()
    object FullScreen : WindowState()
    data class Custom(val width: Dp, val height: Dp) : WindowState()
}