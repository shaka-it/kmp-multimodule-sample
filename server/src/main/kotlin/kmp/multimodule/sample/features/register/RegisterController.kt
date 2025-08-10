package kmp.multimodule.sample.features.register

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import kmp.multimodule.sample.database.tokens.TokenDto
import kmp.multimodule.sample.database.tokens.Tokens
import kmp.multimodule.sample.database.users.UserDto
import kmp.multimodule.sample.database.users.Users
import kmp.multimodule.sample.utils.isValidEmail
import org.jetbrains.exposed.exceptions.ExposedSQLException
import java.util.UUID

class RegisterController(private val call: ApplicationCall) {

    suspend fun registerNewUser() {
        val registerRequest = call.receive<RegisterRequest>()
        if (!registerRequest.email.isValidEmail()) {
            call.respond(HttpStatusCode.BadRequest, "Email is not valid")
        }

        val userDTO = Users.fetchUser(registerRequest.login)
        if (userDTO != null) {
            call.respond(HttpStatusCode.Conflict, "User already exists")
        } else {
            val token = UUID.randomUUID().toString()

            try {
                Users.insert(
                    UserDto(
                        login = registerRequest.login,
                        password = registerRequest.password,
                        email = registerRequest.email,
                        username = registerRequest.username,
                    )
                )
            } catch (e: ExposedSQLException) {
                call.respond(HttpStatusCode.Conflict, "User already exists")
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, "Can't create user ${e.localizedMessage}")
            }

            Tokens.insert(
                TokenDto(
                    rowId = UUID.randomUUID().toString(), login = registerRequest.login,
                    token = token
                )
            )

            call.respond(RegisterResponse(token = token))
        }
    }
}