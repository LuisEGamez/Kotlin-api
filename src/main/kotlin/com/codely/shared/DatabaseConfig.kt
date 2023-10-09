package com.codely.shared

import com.codely.course.infrastruccture.DatabaseConnectionData
import com.codely.course.infrastruccture.InMemoryCourseRepository
import com.codely.course.infrastruccture.PostgresCourseRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

@Configuration
class DatabaseConfig {
    /*@Value("\${database.connection.username}")
    private lateinit var databaseUserName: String

    @Value("\${database.connection.password}")
    private lateinit var databasePassword: String*/

    /*@Bean
    @ConfigurationProperties(prefix = "database.connection")
    fun databaseConnectionData() = DatabaseConnectionData()

    @Bean
    fun courseRepository() = InMemoryCourseRepository(databaseConnectionData())*/

    @Bean
    fun courseRepository(jdbcTemplate: NamedParameterJdbcTemplate) = PostgresCourseRepository(jdbcTemplate)

}