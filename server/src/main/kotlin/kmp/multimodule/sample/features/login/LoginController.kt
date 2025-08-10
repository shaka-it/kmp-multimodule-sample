package kmp.multimodule.sample.features.login

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import kmp.multimodule.sample.database.tokens.TokenDto
import kmp.multimodule.sample.database.tokens.Tokens
import kmp.multimodule.sample.database.users.Users
import java.util.UUID

class LoginController(private val call: ApplicationCall) {

    suspend fun performLogin() {
        val receive = call.receive<LoginRequest>()
        val userDTO = Users.fetchUser(receive.login)

        if (userDTO == null) {
            call.respond(HttpStatusCode.BadRequest, "User not found")
        } else {
            if (userDTO.password == receive.password) {
                val token = UUID.randomUUID().toString()
                Tokens.insert(
                    TokenDto(
                        rowId = UUID.randomUUID().toString(), login = receive.login,
                        token = token
                    )
                )

                call.respond(LoginResponse(token = token))
            } else {
                call.respond(HttpStatusCode.BadRequest, "Invalid password")
            }
        }
    }
}