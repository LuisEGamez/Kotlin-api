package com.codely.course.domain

import java.time.LocalDateTime
import java.util.UUID

data class CourseId(val value: UUID) {
    companion object {
        fun fromString(id: String) = try {
            CourseId(UUID.fromString(id))
        } catch (exception: Exception) {
            throw InvalidCourseIdException(id, exception)
        }
    }
}

data class CourseName(val value: String) {
    init {
        validate()
    }

    private fun validate() {
        if (value.isEmpty() || value.isBlank()) {
            throw InvalidCourseNameException(value)
        }
    }
}

data class CourseDescription(val value: String) {
    init {
        validate()
    }

    private fun validate() {
        if (value.length > 150) {
            throw InvalidCourseDescriptionException(value)
        }
    }
}

data class Course(
    val id: CourseId,
    val name: CourseName,
    val createdAt: LocalDateTime,
    val description: CourseDescription
) {
    companion object {
        fun create(
            id: String,
            name: String,
            createdAt: LocalDateTime,
            description: String
        ): Course {
            return Course(
                CourseId.fromString(id),
                CourseName(name),
                createdAt,
                CourseDescription(description)
            )
        }
    }
}
