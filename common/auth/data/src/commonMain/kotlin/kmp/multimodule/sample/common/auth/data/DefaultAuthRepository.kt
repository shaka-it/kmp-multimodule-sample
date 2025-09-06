package kmp.multimodule.sample.common.auth.data

import kmp.multimodule.sample.common.auth.api.AuthRepository
import kmp.multimodule.sample.common.auth.api.models.Token
import kmp.multimodule.sample.common.auth.data.ktor.KtorAuthRemoteDataSource
import kmp.multimodule.sample.common.auth.data.ktor.KtorLoginRequest
import kmp.multimodule.sample.common.auth.data.settings.SettingsAuthDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DefaultAuthRepository(
    private val remoteDataSource: KtorAuthRemoteDataSource,
    private val cacheDataSource: SettingsAuthDataSource,
    private val ioDispatcher: CoroutineDispatcher,
) : AuthRepository {

    override suspend fun login(login: String, password: String): Token =
        withContext(ioDispatcher) {
            val token = remoteDataSource.performLogin(
                request = KtorLoginRequest(
                    login = login,
                    password = password
                )
            )

            cacheDataSource.saveLogin(login)
            cacheDataSource.saveToken(token.token)
            token
        }

    override fun isUserLoggedIn(): Boolean {
        return cacheDataSource.fetchToken().isNotBlank()
    }

    override fun logout() {
        cacheDataSource.saveLogin("")
        cacheDataSource.saveToken("")
    }

    override fun getToken(): String {
        return cacheDataSource.fetchToken()
    }

    override fun getLogin(): String {
        return cacheDataSource.fetchLogin()
    }
}