package com.codely.shared

import io.mockk.every
import io.mockk.unmockkAll
import java.time.LocalDateTime
import org.junit.jupiter.api.AfterEach

open class BaseTest {

    protected lateinit var clock: Clock

    protected fun givenFixedDate(fixedDateTime: LocalDateTime) {
        every { clock.now() } returns fixedDateTime
    }

    @AfterEach
    protected fun cleanMock() {
        unmockkAll()
    }
}
