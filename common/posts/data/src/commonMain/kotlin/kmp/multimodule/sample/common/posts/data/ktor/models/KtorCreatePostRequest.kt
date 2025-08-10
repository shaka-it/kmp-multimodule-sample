package kmp.multimodule.sample.common.posts.data.ktor.models

import kotlinx.serialization.Serializable

@Serializable
data class KtorCreatePostRequest(
    val title: String,
    val description: String,
)