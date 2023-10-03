package com.codely.course.application

import com.codely.course.domain.Course
import com.codely.course.domain.CourseRepository
import com.codely.shared.Clock
import java.time.LocalDateTime

class CourseCreator(private val repository: CourseRepository, private val clock: Clock) {

    fun create(id: String, name: String, description: String) {
        Course
            .create(
                id,
                name,
                clock.now(),
                description
            ).let {
                repository.save(it)
            }
    }
}