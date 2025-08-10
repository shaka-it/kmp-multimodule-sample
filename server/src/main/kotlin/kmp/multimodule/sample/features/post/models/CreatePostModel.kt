package kmp.multimodule.sample.features.post.models

import kotlinx.serialization.Serializable

@Serializable
data class CreatePostRequest(
    val title: String,
    val description: String,
)

@Serializable
data class CreatePostResponse(
    val postId: String,
    val title: String,
    val description: String,
    val author: String,
)