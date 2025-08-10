package kmp.multimodule.sample.database.posts

import kmp.multimodule.sample.features.post.models.CreatePostRequest
import kmp.multimodule.sample.features.post.models.CreatePostResponse
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class PostDto(
    val postId: String,
    val title: String,
    val description: String,
    val author: String,
)

fun CreatePostRequest.mapToPostDto(
    author: String,
): PostDto =
    PostDto(
        postId = UUID.randomUUID().toString(),
        title = title,
        description = description,
        author = author,
    )

fun PostDto.mapToCreatePostResponse(): CreatePostResponse =
    CreatePostResponse(
        postId = postId,
        title = title,
        description = description,
        author = author,
    )