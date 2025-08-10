package kmp.multimodule.sample.plugins

import kmp.multimodule.sample.POSTGRES_PASSWORD
import kmp.multimodule.sample.POSTGRES_URL
import kmp.multimodule.sample.POSTGRES_USER
import org.jetbrains.exposed.sql.Database

fun configureDatabase() {
    Database.connect(
        url = POSTGRES_URL,
        driver = "org.postgresql.Driver",
        user = POSTGRES_USER,
        password = POSTGRES_PASSWORD,
    )
}