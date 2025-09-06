package kmp.multimodule.sample.common.posts.data.repository

import kmp.multimodule.sample.common.posts.api.models.Post
import kmp.multimodule.sample.common.posts.api.repository.PostsRepository
import kmp.multimodule.sample.common.posts.data.ktor.KtorPostsDataSource
import kmp.multimodule.sample.common.posts.data.ktor.models.KtorCreatePostRequest
import kmp.multimodule.sample.common.posts.data.mapper.PostMapper
import kmp.multimodule.sample.common.posts.data.sqldelight.SqlDelightPostsDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

internal class DefaultPostsRepository(
    private val localDataSource: SqlDelightPostsDataSource,
    private val remoteDataSource: KtorPostsDataSource,
    private val postMapper: PostMapper,
    private val ioDispatcher: CoroutineDispatcher,
) : PostsRepository {
    override suspend fun observePosts(): Flow<List<Post>> =
        localDataSource.fetchAllPosts()
            .map { entities -> entities.map(postMapper::toPost) }
            .flowOn(ioDispatcher)

    override suspend fun refreshPosts() = withContext(ioDispatcher) {
        val response = remoteDataSource.fetchAllPosts().map(postMapper::toPost)
        localDataSource.clearAllPosts()
        response.forEach { post ->
            localDataSource.createPost(post)
        }
    }

    override suspend fun createPost(post: Post) = withContext(ioDispatcher) {
        val request = KtorCreatePostRequest(
            title = post.title,
            description = post.description,
        )

        val response = remoteDataSource.createPost(request).let(postMapper::toPost)
        localDataSource.createPost(response)
    }

    override suspend fun clearCache() = withContext(ioDispatcher) {
        localDataSource.clearAllPosts()
    }

    override suspend fun fetchDemoPosts(): List<Post> = withContext(ioDispatcher) {
        remoteDataSource.fetchDemoPosts().map(postMapper::toPost)
    }
}