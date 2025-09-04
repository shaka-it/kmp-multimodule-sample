package kmp.multimodule.sample

import io.ktor.server.application.Application
import io.ktor.server.netty.EngineMain
import kmp.multimodule.sample.features.login.configureLoginRouting
import kmp.multimodule.sample.features.post.configurePostsRouting
import kmp.multimodule.sample.features.register.configureRegisterRouting
import kmp.multimodule.sample.plugins.configureDatabase
import kmp.multimodule.sample.plugins.configureDemoRouting
import kmp.multimodule.sample.plugins.configureSerialization

fun main(args: Array<String>) = EngineMain.main(args)

fun Application.module() {
    configureDatabase()
    configureSerialization()
    configureDemoRouting()
    configureLoginRouting()
    configureRegisterRouting()
    configurePostsRouting()
}