package com.codely.shared

import com.codely.shared.database.PostgresTestUtils
import com.codely.shared.database.TestConfig
import io.mockk.unmockkAll
import io.restassured.RestAssured
import java.io.File
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.containers.ComposeContainer
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [Application::class])
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Import(TestConfig::class)
@Testcontainers
class BaseAcceptanceTestRestAssure {

    @LocalServerPort
    private val springbootPort: Int = 0

    @Autowired
    private lateinit var postgresTestUtils: PostgresTestUtils

    companion object {
        private const val POSTGRES_PORT = 5432
        val environment: ComposeContainer =
            ComposeContainer(File("docker-compose-test.yml"))
                .withExposedService("db", POSTGRES_PORT, Wait.forListeningPort())
                .withLocalCompose(true)
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
        postgresTestUtils.clean()
    }
}
