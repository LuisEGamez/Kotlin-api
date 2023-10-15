package com.codely.shared

import com.codely.course.application.CourseCreator
import com.codely.course.domain.CourseRepository
import com.codely.course.infrastruccture.ClockImp
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DependencyInjectionConfig {

    @Bean
    fun clock() = ClockImp()

    @Bean
    fun courseCreator(courseRepository: CourseRepository, clock: Clock) = CourseCreator(courseRepository, clock)
}
