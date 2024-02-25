package org.elice.assignment.data.model

import com.google.gson.annotations.SerializedName
import org.elice.assignment.domain.entities.CourseDetailEntity
import org.elice.assignment.domain.entities.CourseEntity

data class EliceCourseListResponse(
    val courses: List<CourseEntity>,
    @SerializedName("course_count")
    val courseCount: Int,
    @SerializedName("_result")
    val result: ResultEntity
)

data class EliceCourseDetailResponse(
    @SerializedName("course")
    val courseDetail: CourseDetailEntity,
    @SerializedName("_result")
    val result: ResultEntity
)

data class ResultEntity(
    val status: String,
    val reason: String?
)
