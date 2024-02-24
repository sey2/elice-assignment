package org.elice.assignment.domain.entities

import com.google.gson.annotations.SerializedName

data class EliceCourseListEntity(
    val courses: List<CourseEntity>,
    val courseCount: Int
)

data class CourseEntity(
    val id: Int,
    val title: String,
    @SerializedName("short_description")
    val shortDescription: String,
    val isRecommended: Boolean,
    val isFree: Boolean,
    val logoFileUrl: String?
)

fun createMockCourseEntity(): List<CourseEntity> = listOf(
    CourseEntity(
        9999,
        "C언어 챌린지C언어 챌린지C언어 챌린지",
        "나의 C언어 실력을 테스트 해보세요!나의 C언어 실력을 테스트 해보세요!",
        false,
        false,
        "mock"
    )
)
