package com.codely.shared

import java.time.LocalDateTime

interface Clock {
    fun now(): LocalDateTime
}