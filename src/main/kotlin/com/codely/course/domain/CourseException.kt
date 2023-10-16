package com.codely.course.domain

sealed class CourseException(override val message: String, override val cause: Throwable? = null) : RuntimeException(message, cause)

data class InvalidCourseIdException(val id: String, override val cause: Throwable?) : CourseException("The id <$id> is not a valid course id", cause)
data class InvalidCourseNameException(val name: String) : CourseException("The name <$name> is not a valid course name")
data class InvalidCourseDescriptionException(val description: String) : CourseException("The description <$description> is too large 150 char max")
data class CourseNotFoundException(val id: CourseId) : CourseException("There is no course with id <${id.value}>")