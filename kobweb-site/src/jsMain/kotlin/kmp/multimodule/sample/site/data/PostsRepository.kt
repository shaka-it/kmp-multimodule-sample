package kmp.multimodule.sample.site.data

import kmp.multimodule.sample.site.api.Api
import kotlinx.serialization.Serializable

@Serializable data class Post(val id: Int, val title: String, val body: String)

object PostsRepository {
    suspend fun load(): List<Post> = Api.get("/demo-posts")
}
