package kmp.multimodule.sample.common.posts.presentation.make

import com.arkivanov.decompose.value.Value

interface MakePostComponent {
    val viewState: Value<ViewState>

    fun onTitleChanged(value: String)

    fun onDescriptionChanged(value: String)

    fun onMakePostClick()

    data class ViewState(
        val title: String = "",
        val description: String = "",
    )
}