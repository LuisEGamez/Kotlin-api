package com.codely.course.infrastruccture.rest.create

import com.codely.course.application.CourseCreator
import com.codely.course.domain.InvalidCourseDescriptionException
import com.codely.course.domain.InvalidCourseIdException
import com.codely.course.domain.InvalidCourseNameException
import java.net.URI
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PostCreateCourseController(private val courseCreator: CourseCreator) {

    @PostMapping("/course")
    fun execute(
        @RequestBody request: CreateCourseRequest
    ): ResponseEntity<String> {
        return try {
            courseCreator.create(request.id, request.name, request.description)
            ResponseEntity.created(URI.create("/course/${request.id}")).build()
        } catch (exception: Throwable) {
            when (exception) {
                is InvalidCourseIdException -> ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("The course id is not valid")

                is InvalidCourseNameException -> ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("The course name is not valid")

                is InvalidCourseDescriptionException -> ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("The course description is not valid")

                else -> ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build()
            }
        }
    }
}

data class CreateCourseRequest(
    val id: String = "",
    val name: String = "",
    val description: String = ""
)
