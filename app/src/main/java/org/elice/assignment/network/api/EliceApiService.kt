package org.elice.assignment.network.api

import org.elice.assignment.network.model.EliceCourseDetailResponse
import org.elice.assignment.network.model.EliceCourseListResponse
import org.elice.assignment.network.model.EliceLectureListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface EliceApiService {
    @GET("org/academy/course/list/")
    suspend fun getCourseList(
        @Query("offset") offset: Int,
        @Query("count") count: Int,
        @Query("filter_is_recommended") filterIsRecommended: Boolean? = null,
        @Query("filter_is_free") filterIsFree: Boolean? = null,
        @Query("filter_conditions") filterConditions: String? = null
    ): EliceCourseListResponse

    @GET("org/academy/course/get/")
    suspend fun getCourse(
        @Query("course_id") courseId: Int
    ): EliceCourseDetailResponse

    @GET("org/academy/lecture/list/")
    suspend fun getLectureList(
        @Query("offset") offset: Int,
        @Query("count") count: Int,
        @Query("course_id") courseId: Int
    ): EliceLectureListResponse

}