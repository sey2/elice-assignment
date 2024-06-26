package org.elice.assignment.data.source.remote

import org.elice.assignment.network.api.EliceApiService
import javax.inject.Inject

class EliceCourseRemoteSource @Inject constructor(
    private val eliceCourseService: EliceApiService
) {
    suspend fun getCourseList(
        offset: Int,
        count: Int,
        filterIsRecommended: Boolean? = null,
        filterIsFree: Boolean? = null,
        filterConditions: String? = null
    ) = eliceCourseService.getCourseList(
        offset,
        count,
        filterIsRecommended,
        filterIsFree,
        filterConditions
    )

    suspend fun getCourse(
        courseId: Int
    ) = eliceCourseService.getCourse(courseId).courseDetail

}