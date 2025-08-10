package kmp.multimodule.sample.utils

import kmp.multimodule.sample.database.tokens.Tokens

object TokenCheck {

    fun isTokenValid(token: String): Boolean =
        Tokens.fetchTokens().firstOrNull { it.token == token } != null
}