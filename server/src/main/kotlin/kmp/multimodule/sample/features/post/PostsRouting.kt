package kmp.multimodule.sample.features.post

import io.ktor.server.application.Application
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing

fun Application.configurePostsRouting() {

    routing {
        post("/posts/create") {
            PostsController(call).createPost()
        }

        get("/posts") {
            PostsController(call).fetchPosts()
        }
    }
}