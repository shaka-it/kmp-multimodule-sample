package kmp.multimodule.sample.database.tokens

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object Tokens : Table() {
    private val id = Tokens.varchar("id", 50)
    private val login = Tokens.varchar("login", 25)
    private val token = Tokens.varchar("token", 50)

    fun insert(tokenDto: TokenDto) {
        transaction {
            insert {
                it[id] = tokenDto.rowId
                it[login] = tokenDto.login
                it[token] = tokenDto.token
            }
        }
    }

    fun fetchTokens(): List<TokenDto> {
        return try {
            transaction {
                Tokens.selectAll().toList()
                    .map {
                        TokenDto(
                            rowId = it[Tokens.id],
                            token = it[token],
                            login = it[login]
                        )
                    }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun fetchTokenDtoByToken(tokenValue: String): TokenDto? {
        return try {
            transaction {
                Tokens
                    .select { token eq tokenValue }
                    .map { row ->
                        TokenDto(
                            rowId = row[Tokens.id],
                            token = row[token],
                            login = row[login]
                        )
                    }
                    .singleOrNull()
            }
        } catch (e: Exception) {
            null
        }
    }
}