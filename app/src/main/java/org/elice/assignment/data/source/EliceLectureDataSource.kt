package org.elice.assignment.data.source

import org.elice.assignment.network.api.EliceApiService
import javax.inject.Inject

class EliceLectureDataSource @Inject constructor(
    private val eliceLectureService: EliceApiService
) {
    suspend fun getLectureList(
        offset: Int,
        count: Int,
        courseId: Int
    ) = eliceLectureService.getLectureList(
        offset,
        count,
        courseId
    ).lectures
}