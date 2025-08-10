package kmp.multimodule.sample.common.auth.data.settings

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get

class SettingsAuthDataSource(
    private val settings: Settings,
) {

    fun saveToken(token: String) {
        settings.putString(tokenKey, token)
    }

    fun fetchToken(): String {
        return settings[tokenKey, ""]
    }

    fun saveLogin(login: String) {
        settings.putString(loginKey, login)
    }

    fun fetchLogin(): String {
        return settings[loginKey, ""]
    }

    companion object {
        private val tokenKey = "tokenKey"
        private val loginKey = "login"
    }
}