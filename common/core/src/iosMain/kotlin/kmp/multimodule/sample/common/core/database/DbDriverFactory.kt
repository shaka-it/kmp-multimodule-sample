package kmp.multimodule.sample.common.core.database

import kmp.multimodule.sample.common.core.PlatformConfiguration
import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.native.NativeSqliteDriver

actual class DbDriverFactory actual constructor(private val platformConfiguration: PlatformConfiguration) {
    actual suspend fun provideDbDriver(
        schema: SqlSchema<QueryResult.AsyncValue<Unit>>,
    ): SqlDriver = NativeSqliteDriver(schema.synchronous(), "local.db")
}