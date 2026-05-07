package com.n26.mentoring

import org.flywaydb.core.Flyway
import org.postgresql.ds.PGSimpleDataSource
import org.testcontainers.containers.PostgreSQLContainer

object PostgresTestContainer {
    val container: PostgreSQLContainer<Nothing> =
        PostgreSQLContainer<Nothing>("postgres:16-alpine").apply {
            withDatabaseName("mentoring")
            withUsername("mentoring")
            withPassword("mentoring")
        }

    init {
        container.start()
    }

    fun dataSource() =
        PGSimpleDataSource().apply {
            setURL(container.jdbcUrl)
            user = container.username
            password = container.password
        }

    fun migrateClean() {
        Flyway.configure()
            .dataSource(dataSource())
            .cleanDisabled(false)
            .load()
            .also {
                it.clean()
                it.migrate()
            }
    }
}
