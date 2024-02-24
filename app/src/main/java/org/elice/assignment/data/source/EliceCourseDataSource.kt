package org.elice.assignment.data.source

import org.elice.assignment.data.api.EliceApiService

class EliceCourseDataSource(
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