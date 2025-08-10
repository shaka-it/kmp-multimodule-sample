package kmp.multimodule.sample.common.posts.presentation.posts

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class FakePostsComponent : PostsComponent {
    override val viewState: Value<PostsComponent.ViewState>
        get() = MutableValue(PostsComponent.ViewState())

    override fun onRefreshPosts() = Unit
}