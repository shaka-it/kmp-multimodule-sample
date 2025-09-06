package sample.data

import sample.api.Api
import kotlinx.serialization.Serializable

@Serializable data class Post(val id: Int, val title: String, val body: String)

object PostsRepository {
    suspend fun load(): List<Post> = Api.get("/demo-posts")
}
