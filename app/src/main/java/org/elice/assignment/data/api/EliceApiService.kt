package org.elice.assignment.data.api

import org.elice.assignment.data.model.EliceCourseListResponse
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

}