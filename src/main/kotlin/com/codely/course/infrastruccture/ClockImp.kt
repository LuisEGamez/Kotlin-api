package com.codely.course.infrastruccture

import com.codely.shared.Clock
import org.springframework.stereotype.Component
import java.time.LocalDateTime

class ClockImp: Clock {
    override fun now(): LocalDateTime {
        return LocalDateTime.now()
    }
}