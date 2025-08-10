package kmp.multimodule.sample.common.posts.data.ktor

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.path
import kmp.multimodule.sample.common.auth.api.AuthRepository
import kmp.multimodule.sample.common.posts.data.ktor.models.KtorCreatePostRequest
import kmp.multimodule.sample.common.posts.data.ktor.models.KtorFetchPostResponse

class KtorPostsDataSource(
    private val authRepository: AuthRepository,
    private val httpClient: HttpClient,
) {
    suspend fun fetchAllPosts(): List<KtorFetchPostResponse> {
        return httpClient.get {
            header("Bearer-Authorization", authRepository.getToken())

            url {
                path("posts")
            }
        }.body()
    }

    suspend fun createPost(request: KtorCreatePostRequest): KtorFetchPostResponse {
        return httpClient.post {
            header("Bearer-Authorization", authRepository.getToken())

            url {
                path("posts/create")
                setBody(request)
            }
        }.body()
    }
}