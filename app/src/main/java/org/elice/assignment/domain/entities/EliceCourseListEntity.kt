package org.elice.assignment.domain.entities

data class EliceCourseListEntity(
    val courses: List<CourseEntity>,
    val courseCount: Int
)

data class CourseEntity(
    val id: Int,
    val title: String,
    val shortDescription: String,
    val isRecommended: Boolean,
    val isFree: Boolean,
    val logoFileUrl: String?
)
