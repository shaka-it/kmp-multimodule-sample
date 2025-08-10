package kmp.multimodule.sample.features.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val login: String,
    val email: String,
    val password: String,
    val username: String,
)

@Serializable
data class RegisterResponse(
    val token: String
)
