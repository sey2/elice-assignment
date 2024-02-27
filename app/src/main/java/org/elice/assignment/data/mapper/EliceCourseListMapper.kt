package org.elice.assignment.data.mapper

import org.elice.assignment.data.model.EliceCourseListResponse
import org.elice.assignment.domain.entities.CourseEntity

object EliceCourseListMapper {
    fun EliceCourseListResponse.toDomain() =
        courses.map { course ->
            CourseEntity(
                id = course.id,
                title = course.title,
                shortDescription = course.shortDescription ?: "",
                isRecommended = course.isRecommended,
                isFree = course.isFree,
                logoFileUrl = course.logoFileUrl,
                imageFileUrl = course.imageFileUrl,
                tagList = listOf()
            )
        }
}