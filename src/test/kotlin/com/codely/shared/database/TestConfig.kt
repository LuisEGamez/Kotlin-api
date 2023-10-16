package com.codely.shared.database

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TestConfig {
    @Bean
    fun postgresTestUtils() = PostgresTestUtils()
}
