package kmp.multimodule.sample.common.posts.data.repository

import kmp.multimodule.sample.common.posts.api.models.Post
import kmp.multimodule.sample.common.posts.api.repository.PostsRepository
import kmp.multimodule.sample.common.posts.data.ktor.KtorPostsDataSource
import kmp.multimodule.sample.common.posts.data.ktor.models.KtorCreatePostRequest
import kmp.multimodule.sample.common.posts.data.mapper.PostMapper
import kmp.multimodule.sample.common.posts.data.sqldelight.SqlDelightPostsDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

internal class DefaultPostsRepository(
    private val localDataSource: SqlDelightPostsDataSource,
    private val remoteDataSource: KtorPostsDataSource,
    private val postMapper: PostMapper,
    private val coroutineContext: CoroutineContext,
) : PostsRepository {
    override suspend fun observePosts(): Flow<List<Post>> =
        localDataSource.fetchAllPosts()
            .map { entities -> entities.map(postMapper::toPost) }
            .flowOn(coroutineContext)

    override suspend fun refreshPosts() = withContext(coroutineContext) {
        val responses = remoteDataSource.fetchAllPosts().map(postMapper::toPost)
        localDataSource.clearAllPosts()
        responses.forEach { post ->
            localDataSource.createPost(post)
        }
    }

    override suspend fun createPost(post: Post) = withContext(coroutineContext) {
        val request = KtorCreatePostRequest(
            title = post.title,
            description = post.description,
        )

        val response = remoteDataSource.createPost(request).let(postMapper::toPost)
        localDataSource.createPost(response)
    }

    override suspend fun clearCache() = withContext(coroutineContext) {
        localDataSource.clearAllPosts()
    }
}