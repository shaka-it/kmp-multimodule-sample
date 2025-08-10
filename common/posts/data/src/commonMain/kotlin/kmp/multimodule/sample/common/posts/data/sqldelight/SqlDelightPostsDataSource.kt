package kmp.multimodule.sample.common.posts.data.sqldelight

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kmp.multimodule.sample.common.core.database.DbDriverFactory
import kmp.multimodule.sample.db.AppDatabase
import kmp.multimodule.sample.db.AppDatabaseQueries
import kmp.multimodule.sample.db.PostEntity
import kotlinx.coroutines.flow.Flow
import kmp.multimodule.sample.common.posts.api.models.Post
import kotlin.coroutines.CoroutineContext

internal class SqlDelightPostsDataSource(
    private val driverFactory: DbDriverFactory,
    private val coroutineContext: CoroutineContext,
) {

    private lateinit var queries: AppDatabaseQueries

    suspend fun fetchAllPosts(): Flow<List<PostEntity>> {
        return getQueries().selectAll().asFlow().mapToList(coroutineContext)
    }

    suspend fun createPost(post: Post) {
        getQueries().insert(
            id = post.id,
            title = post.title,
            description = post.description,
            author = post.author,
        )
    }

    suspend fun clearAllPosts() {
        getQueries().deleteAll()
    }

    private suspend fun getQueries(): AppDatabaseQueries {
        if (this::queries.isInitialized.not()) {
            queries = AppDatabase.Companion(driverFactory.provideDbDriver(AppDatabase.Companion.Schema)).appDatabaseQueries
        }

        return queries
    }
}