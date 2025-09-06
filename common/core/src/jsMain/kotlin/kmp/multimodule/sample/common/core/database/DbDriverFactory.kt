package kmp.multimodule.sample.common.core.database

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import kmp.multimodule.sample.common.core.PlatformConfiguration

actual class DbDriverFactory actual constructor(platformConfiguration: PlatformConfiguration) {
    actual suspend fun provideDbDriver(schema: SqlSchema<QueryResult.AsyncValue<Unit>>): SqlDriver {
        TODO("Not yet implemented")
    }
}