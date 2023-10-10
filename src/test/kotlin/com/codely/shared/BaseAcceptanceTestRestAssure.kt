package com.codely.shared

import io.mockk.unmockkAll
import io.restassured.RestAssured
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.containers.ComposeContainer
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.junit.jupiter.Testcontainers
import java.io.File
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Testcontainers
class BaseAcceptanceTestRestAssure {

    @LocalServerPort
    private val springbootPort: Int = 0

    companion object {
        private const val POSTGRES_PORT = 5432
        val environment: ComposeContainer =
            ComposeContainer(File("docker-compose.yml"))
                .withExposedService("db", POSTGRES_PORT, Wait.forListeningPort())
    }

    @PostConstruct
    fun start() {
        environment.start()
    }

    @PreDestroy
    fun stop() {
        environment.stop()
    }

    @BeforeEach
    fun setUp() {
        RestAssured.port = springbootPort
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }
}