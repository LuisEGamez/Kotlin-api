package com.codely.course.infrastruccture

import com.codely.course.BaseIntegrationTest
import com.codely.course.domain.Course
import com.codely.course.domain.CourseDescription
import com.codely.course.domain.CourseId
import com.codely.course.domain.CourseName
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet
import java.time.LocalDateTime


class PostgresCourseRepositoryTest : BaseIntegrationTest() {

    @Autowired
    private lateinit var repository: PostgresCourseRepository

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    @Test
    fun `should save a course`() {
        val courseId = "13590efb-c181-4c5f-9f95-b768abde13e2"
        val courseToSave =
            Course(CourseId.fromString(courseId), CourseName("Test"), LocalDateTime.now(), CourseDescription("Hola"))
        repository.save(courseToSave)

        val courseFromDb = jdbcTemplate.queryForObject(
            "select * from course where id=?",
            mapRow(),
            courseId
        )

        assertEquals(courseToSave, courseFromDb)
    }

    private fun mapRow(): RowMapper<Course> {
        return RowMapper { rs: ResultSet, _: Int ->
            val createdAt = rs.getTimestamp("created_at").toLocalDateTime()
            Course.create(rs.getString("id"), rs.getString("name"), createdAt, rs.getString("description"))
        }
    }
}