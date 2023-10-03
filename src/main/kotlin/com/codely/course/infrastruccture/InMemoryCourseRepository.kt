package com.codely.course.infrastruccture

import com.codely.course.domain.Course
import com.codely.course.domain.CourseRepository
import org.springframework.stereotype.Component

class DatabaseConnectionData(var username: String = "", var password: String = "")

class InMemoryCourseRepository(private val connectionData: DatabaseConnectionData): CourseRepository {

    init {
        println()
    }

    override fun save(course: Course) {
        TODO("Not yet implemented")
    }
}