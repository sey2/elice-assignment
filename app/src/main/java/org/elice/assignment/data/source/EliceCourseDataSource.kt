package org.elice.assignment.data.source

import org.elice.assignment.data.api.EliceApiService
import javax.inject.Inject

class EliceCourseDataSource @Inject constructor(
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
    ).courses
}