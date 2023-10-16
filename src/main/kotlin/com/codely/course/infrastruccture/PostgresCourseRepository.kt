package com.codely.course.infrastruccture

import com.codely.course.domain.Course
import com.codely.course.domain.CourseDescription
import com.codely.course.domain.CourseId
import com.codely.course.domain.CourseName
import com.codely.course.domain.CourseRepository
import java.sql.ResultSet
import java.util.UUID
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

class PostgresCourseRepository(
    private val jdbcTemplate: NamedParameterJdbcTemplate
) : CourseRepository {

    override fun save(course: Course) {
        MapSqlParameterSource()
            .addValue("id", course.id.value)
            .addValue("name", course.name.value)
            .addValue("createdAt", course.createdAt)
            .addValue("description", course.description.value)
            .let { params ->
                jdbcTemplate.update(
                    "INSERT INTO course (id, name, created_at, description) VALUES (:id,:name,:createdAt,:description)",
                    params
                )
            }
    }

    override fun find(id: CourseId): Course? {
        val query = "SELECT * FROM course where id=:id"
        val params = MapSqlParameterSource().addValue("id", id.value.toString())
        return try {
            jdbcTemplate
                .queryForObject(query, params, mapRow())
        } catch (emptyResultException: EmptyResultDataAccessException) {
            null
        }
    }

    private fun mapRow(): RowMapper<Course> {
        return RowMapper { rs: ResultSet, _: Int ->
            val id = CourseId(UUID.fromString(rs.getString("id")))
            val name = CourseName(rs.getString("name"))
            val createdAt = rs.getTimestamp("created_at").toLocalDateTime()
            val description = CourseDescription(rs.getString("description"))
            Course(id, name, createdAt, description)
        }
    }
}
