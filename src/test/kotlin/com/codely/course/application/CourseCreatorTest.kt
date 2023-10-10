package com.codely.course.application

import com.codely.shared.BaseTest
import com.codely.course.domain.Course
import com.codely.course.domain.CourseRepository
import com.codely.course.domain.InvalidCourseIdException
import com.codely.course.domain.InvalidCourseNameException
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime

class CourseCreatorTest : BaseTest() {

    private lateinit var courseRepository: CourseRepository
    private lateinit var courseCreator: CourseCreator

    @BeforeEach
    fun setUp() {
        courseRepository = mockk(relaxUnitFun = true)
        clock = mockk()
        courseCreator = CourseCreator(courseRepository, clock)
    }

    @Test
    fun `should create a course`() {
        givenFixedDate(fixedDate)

        courseCreator.create(id, name, description)

        thenTheCourseShouldBeSaved()

    }

    @Test
    fun `should fail with invalid id`() {
        givenFixedDate(fixedDate)

        assertThrows<InvalidCourseIdException> { courseCreator.create("invalidId", name, description) }

    }

    @Test
    fun `should fail with invalid name`() {
        givenFixedDate(fixedDate)

        assertThrows<InvalidCourseNameException> { courseCreator.create(id, "", description) }


    }

    @Test
    fun `should fail with invalid description`() {

        var description = "0"

        for (i in 0..151){
            description += i.toString()
        }

        givenFixedDate(fixedDate)

        assertThrows<InvalidCourseNameException> { courseCreator.create(id, "", description) }


    }

    private fun thenTheCourseShouldBeSaved() {
        verify {
            courseRepository.save(
                Course
                    .create(
                        id = id,
                        name = name,
                        createdAt = fixedDate,
                        description
                    )
            )
        }
    }


    companion object {
        private const val id = "73ff3284-76a7-49b8-992c-bd80a05b41b9"
        private const val name = "Kotlin Hexagonal Architecture"
        private const val description = "This is a description"
        private val fixedDate = LocalDateTime.parse("2022-08-09T14:50:42")
    }
}