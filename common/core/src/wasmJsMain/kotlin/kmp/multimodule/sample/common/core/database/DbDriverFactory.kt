package kmp.multimodule.sample.common.core.database

import kmp.multimodule.sample.common.core.PlatformConfiguration
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.worker.WebWorkerDriver
import org.w3c.dom.Worker

private val sqljsWorkerUrl: String =
    js("new URL('@cashapp/sqldelight-sqljs-worker/sqljs.worker.js', import.meta.url).toString()")

actual class DbDriverFactory actual constructor(private val platformConfiguration: PlatformConfiguration) {
    actual suspend fun provideDbDriver(
        schema: SqlSchema<QueryResult.AsyncValue<Unit>>
    ): SqlDriver = WebWorkerDriver(Worker(sqljsWorkerUrl))
}