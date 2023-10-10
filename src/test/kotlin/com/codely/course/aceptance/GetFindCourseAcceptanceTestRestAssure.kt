package com.codely.course.aceptance

import com.codely.course.domain.CourseMother
import com.codely.course.infrastruccture.PostgresCourseRepository
import com.codely.course.shared.isEqualToJson
import com.codely.shared.BaseAcceptanceTestRestAssure
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.test.context.jdbc.Sql
import java.time.LocalDateTime

class GetFindCourseAcceptanceTestRestAssure : BaseAcceptanceTestRestAssure(){

    @Autowired
    private lateinit var courseRepository: PostgresCourseRepository

    @Test
    @Sql("classpath:db/fixtures/find/add-course-data.sql")
    fun `should find course successfully with fixture`() {
        When {
            get("/course/${course.id}")
        } Then {
            statusCode(HttpStatus.OK.value())
        } Extract {
            body().asString().isEqualToJson(expectedCourseResponse)
        }
    }

    @Test
    fun `should find course successfully with course creation`() {
        Given {
            `an existing course`()
            contentType(ContentType.JSON)
            body("")
        } When {
            get("/course/${course.id}")
        } Then {
            statusCode(HttpStatus.OK.value())
        } Extract {
            body().asString().isEqualToJson(expectedCourseResponse)
        }
    }

    private fun `an existing course`() {
        courseRepository.save(course)
    }

    companion object {
        private val now = LocalDateTime.parse("2022-08-31T09:07:36")
        private val course = CourseMother.sample(
            id = "f2fe1e4e-1e8f-493b-ac67-2c88090cae0a",
            name = "Saved course",
            createdAt = now,
            description = "Test bla bla"
        )
        private val expectedCourseResponse = """
                {
                    "id": "${course.id.value}",
                    "name": "${course.name.value}",
                    "created_at": "$now",
                    "description": "${course.description.value}"
                }
            """.trimIndent()
    }

}