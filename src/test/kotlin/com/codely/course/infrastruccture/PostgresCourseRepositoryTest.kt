package com.codely.course.infrastruccture

import com.codely.course.domain.*
import com.codely.shared.BaseIntegrationTest
import java.sql.ResultSet
import java.time.LocalDateTime
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import kotlin.test.assertNull

class PostgresCourseRepositoryTest : BaseIntegrationTest() {

    @Autowired
    private lateinit var repository: PostgresCourseRepository


    @Test
    fun `should save a course`() {

        repository.save(courseToSave)

    }

    @Test
    fun `should find a course`() {
        repository.save(courseToSave)

        val courseFromDb = repository.find(CourseId.fromString(courseId))

        assertEquals(courseToSave, courseFromDb)
    }

    @Test
    fun `should not find a course`() {

        assertNull(repository.find(CourseId.fromString(courseId)))
    }

    companion object{
        const val courseId = "13590efb-c181-4c5f-9f95-b768abde13e2"
        private val courseToSave = CourseMother.sample(courseId)
    }
}
