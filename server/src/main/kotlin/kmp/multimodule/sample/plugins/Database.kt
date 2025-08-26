package kmp.multimodule.sample.plugins

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.Application
import org.jetbrains.exposed.sql.Database

fun Application.configureDatabase() {
    val config = HikariConfig().apply {
        val db = environment.config.config("ktor.db")

        jdbcUrl = db.property("jdbcUrl").getString()
        username = db.property("user").getString()
        password = db.property("password").getString()
        driverClassName = "org.postgresql.Driver"
        maximumPoolSize = db.property("poolSize").getString().toInt()
        isAutoCommit = false
    }
    Database.connect(HikariDataSource(config))
}