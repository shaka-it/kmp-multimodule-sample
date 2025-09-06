package sample.data

import kotlinx.serialization.Serializable
import sample.api.Api

@Serializable
data class Post(val id: Int, val title: String, val body: String)

object PostsRepository {
    suspend fun load(): List<Post> = Api.get("/demo-posts")
}
