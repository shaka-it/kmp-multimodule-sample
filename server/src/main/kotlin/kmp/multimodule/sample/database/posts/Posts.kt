package kmp.multimodule.sample.database.posts

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object Posts : Table() {
    private val postId = Posts.varchar(name = "postId", length = 100)
    private val title = Posts.varchar(name = "title", length = 150)
    private val description = Posts.varchar(name = "description", length = 500)
    private var author = Posts.varchar(name = "author", length = 100)

    fun insert(gameDto: PostDto) {
        transaction {
            insert {
                it[postId] = gameDto.postId
                it[title] = gameDto.title
                it[description] = gameDto.description
                it[author] = gameDto.author
            }
        }
    }

    fun fetchAll(): List<PostDto> {
        return try {
            transaction {
                Posts.selectAll().toList()
                    .map {
                        PostDto(
                            postId = it[postId],
                            title = it[title],
                            description = it[description],
                            author = it[author],
                        )
                    }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}