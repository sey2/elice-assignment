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
    @SerializedName("logo_file_url")
    val logoFileUrl: String?,
    @SerializedName("image_file_url")
    val imageFileUrl: String?,
    @SerializedName("taglist")
    val tagList: List<String>
)

fun createMockCourseEntity(): List<CourseEntity> = listOf(
    CourseEntity(
        9999,
        "C언어 챌린지C언어 챌린지C언어 챌린지",
        "나의 C언어 실력을 테스트 해보세요!나의 C언어 실력을 테스트 해보세요!",
        false,
        false,
        "https://cdn-api.elice.io/api/file/3053ea72ad42436b8733aa5c286e4b4c/mining",
        imageFileUrl = null,
        listOf()
    )
)
