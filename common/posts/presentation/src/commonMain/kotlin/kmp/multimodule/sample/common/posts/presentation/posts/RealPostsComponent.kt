package kmp.multimodule.sample.common.posts.presentation.posts

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import kmp.multimodule.sample.common.core.presentation.utils.tryToUpdate
import kmp.multimodule.sample.common.posts.api.repository.PostsRepository
import kmp.multimodule.sample.common.posts.presentation.mapper.PostDvoMapper
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class RealPostsComponent(
    componentContext: ComponentContext,
    mainContext: CoroutineContext,
    private val postsRepository: PostsRepository,
    private val postDvoMapper: PostDvoMapper,
) : PostsComponent, ComponentContext by componentContext {
    private val scope = coroutineScope(mainContext + SupervisorJob())

    override val viewState: Value<PostsComponent.ViewState> =
        MutableValue(PostsComponent.ViewState())

    init {
        observePosts()
        onRefreshPosts()
    }

    override fun onRefreshPosts() {
        scope.launch {
            try {
                postsRepository.refreshPosts()
            } catch (_: Throwable) { }
        }
    }

    fun observePosts() {
        scope.launch {
            try {
                postsRepository.observePosts()
                    .map {
                        it.map(postDvoMapper::toPostDvo)
                    }
                    .collect { postList ->
                        viewState.tryToUpdate {
                            it.copy(posts = postList)
                        }
                    }
            } catch (_: Throwable) { }
        }
    }
}