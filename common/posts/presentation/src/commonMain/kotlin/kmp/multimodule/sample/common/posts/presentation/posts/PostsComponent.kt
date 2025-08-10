package kmp.multimodule.sample.common.posts.presentation.posts

import com.arkivanov.decompose.value.Value
import kmp.multimodule.sample.common.posts.presentation.model.PostDvo

interface PostsComponent {
    val viewState: Value<ViewState>

    fun onRefreshPosts()

    data class ViewState(
        val posts: List<PostDvo> = emptyList(),
    )
}