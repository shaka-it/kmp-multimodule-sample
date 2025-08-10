package kmp.multimodule.sample.common.posts.data.ktor.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KtorFetchPostResponse(
    @SerialName("postId") val postId: String,
    @SerialName("title") val title: String,
    @SerialName("description") val description: String,
    @SerialName("author") val author: String,
)