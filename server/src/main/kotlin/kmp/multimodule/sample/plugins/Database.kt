package kmp.multimodule.sample.plugins

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database

fun configureDatabase() {
    val config = HikariConfig().apply {
        jdbcUrl = System.getenv("DATABASE_CONNECTION_STRING")
        username = System.getenv("POSTGRES_USER")
        password = System.getenv("POSTGRES_PASSWORD")
        driverClassName = "org.postgresql.Driver"
        maximumPoolSize = System.getenv("DB_POOL_SIZE").toInt()
        isAutoCommit = false
    }
    Database.connect(HikariDataSource(config))
}