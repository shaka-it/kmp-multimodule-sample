package kmp.multimodule.sample.common.posts.api.models

data class Post(
    val id: String = "",
    val title: String,
    val description: String,
    val author: String = "",
)