package kmp.multimodule.sample.common.auth.api

import kmp.multimodule.sample.common.auth.api.models.Token

interface AuthRepository {
    suspend fun login(login: String, password: String): Token
    fun isUserLoggedIn(): Boolean
    fun logout()
    fun getToken(): String
    fun getLogin(): String
}