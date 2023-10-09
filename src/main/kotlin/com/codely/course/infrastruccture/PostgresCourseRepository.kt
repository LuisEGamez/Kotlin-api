package com.codely.course.infrastruccture

import com.codely.course.domain.Course
import com.codely.course.domain.CourseRepository
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

class PostgresCourseRepository(
    private val jdbcTemplate: NamedParameterJdbcTemplate
) : CourseRepository{

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
}