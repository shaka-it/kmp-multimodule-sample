package kmp.multimodule.sample.common.posts.api.repository

import kmp.multimodule.sample.common.posts.api.models.Post
import kotlinx.coroutines.flow.Flow

interface PostsRepository {
    suspend fun observePosts(): Flow<List<Post>>
    suspend fun refreshPosts()
    suspend fun createPost(post: Post)
    suspend fun clearCache()
}
