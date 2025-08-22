package kmp.multimodule.sample

import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kmp.multimodule.sample.features.login.configureLoginRouting
import kmp.multimodule.sample.features.post.configurePostsRouting
import kmp.multimodule.sample.features.register.configureRegisterRouting
import kmp.multimodule.sample.plugins.configureDatabase
import kmp.multimodule.sample.plugins.configureRouting
import kmp.multimodule.sample.plugins.configureSerialization

fun main() {
    embeddedServer(
        factory = Netty,
        port = System.getenv("SERVER_PORT").toInt(),
        module = Application::module,
    ).start(wait = true)
}

fun Application.module() {
    configureDatabase()
    configureSerialization()
    configureRouting()
    configureLoginRouting()
    configureRegisterRouting()
    configurePostsRouting()
}