package org.elice.assignment.data.mapper

import org.elice.assignment.data.model.EliceCourseListResponse
import org.elice.assignment.domain.entities.CourseEntity
import org.elice.assignment.domain.entities.EliceCourseListEntity

object EliceCourseListMapper {
    fun EliceCourseListResponse.toDomain() = EliceCourseListEntity(
        courses = courses.map { course ->
            CourseEntity(
                id = course.id,
                title = course.title,
                shortDescription = course.shortDescription ?: "",
                isRecommended = course.isRecommended,
                isFree = course.isFree,
                logoFileUrl = course.logoFileUrl
            )
        },
        courseCount = courseCount
    )
}