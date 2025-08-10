package kmp.multimodule.sample

const val SERVER_PORT = 8080

// PostgreSQL connection settings:
// 1. Install pgAdmin (or any Postgres client) and register your database server.
// 2. Create a new database and define your tables.
// 3. Run any migration or seed scripts to populate initial data.
// 4. Enter your JDBC URL, database username, and password below.
const val POSTGRES_URL = "jdbc:postgresql://localhost:5433/kmp-sample"
const val POSTGRES_USER = "postgres"
const val POSTGRES_PASSWORD = "12345678"