/*
package com.codely.shared

import io.mockk.unmockkAll
import java.io.File
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy
import org.junit.jupiter.api.AfterEach
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.containers.ComposeContainer
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest(classes = [Application::class])
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Testcontainers
class BaseAcceptanceTest {

    companion object {
        private const val POSTGRES_PORT = 5432
        val environment: ComposeContainer =
            ComposeContainer(File("docker-compose.yml"))
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

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }
}
*/
