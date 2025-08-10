package kmp.multimodule.sample.database.users

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object Users : Table() {
    private val login = Users.varchar("login", 25)
    private val password = Users.varchar("password", 25)
    private val username = Users.varchar("username", 30)
    private val email = Users.varchar("email", 25)

    fun insert(userDto: UserDto) {
        transaction {
            insert {
                it[login] = userDto.login
                it[password] = userDto.password
                it[username] = userDto.username
                it[email] = userDto.email ?: ""
            }
        }
    }

    fun fetchUser(login: String): UserDto? {
        return try {
            transaction {
                val userModel = Users.select { Users.login.eq(login) }.single()
                UserDto(
                    login = userModel[Users.login],
                    password = userModel[password],
                    username = userModel[username],
                    email = userModel[email]
                )
            }
        } catch (e: Exception) {
            null
        }
    }
}