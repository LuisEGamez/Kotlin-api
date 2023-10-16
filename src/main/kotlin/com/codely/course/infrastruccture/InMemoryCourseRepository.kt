package com.codely.course.infrastruccture

import com.codely.course.domain.Course
import com.codely.course.domain.CourseId
import com.codely.course.domain.CourseRepository

class DatabaseConnectionData(var username: String = "", var password: String = "")

class InMemoryCourseRepository(private val connectionData: DatabaseConnectionData) : CourseRepository {

    init {
        println()
    }

    override fun save(course: Course) {
        TODO("Not yet implemented")
    }

    override fun find(id: CourseId): Course? {
        TODO("Not yet implemented")
    }
}
