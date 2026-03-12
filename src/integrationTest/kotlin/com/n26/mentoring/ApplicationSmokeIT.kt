package com.n26.mentoring

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@SpringBootTest
class ApplicationSmokeIT {
    companion object {
        @Container
        val postgres =
            PostgreSQLContainer<Nothing>("postgres:16-alpine").apply {
                withDatabaseName("mentoring")
                withUsername("mentoring")
                withPassword("mentoring")
            }
    }

    @Test
    fun `application context loads`() {
    }
}
