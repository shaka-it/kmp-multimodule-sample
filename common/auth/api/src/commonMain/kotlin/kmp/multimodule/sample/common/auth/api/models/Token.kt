package kmp.multimodule.sample.common.auth.api.models

import kotlinx.serialization.Serializable

@Serializable
data class Token(
    val token: String
)