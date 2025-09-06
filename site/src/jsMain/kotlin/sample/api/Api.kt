package sample.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.js.Js
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object Api {
    private const val BASE_URL = "https://impressive-wanids-shaka-it-7534c848.koyeb.app"

    private val json = Json { ignoreUnknownKeys = true }

    val client: HttpClient = HttpClient(Js) {
        install(ContentNegotiation) { json(json) }
        install(HttpTimeout) {
            requestTimeoutMillis = 8_000
            connectTimeoutMillis = 5_000
            socketTimeoutMillis = 8_000
        }
    }

    fun url(path: String) = if (path.startsWith("/")) BASE_URL + path else "$BASE_URL/$path"

    suspend inline fun <reified T> get(path: String): T = client.get(url(path)).body()
}