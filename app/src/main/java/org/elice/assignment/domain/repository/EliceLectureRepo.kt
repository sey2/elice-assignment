package org.elice.assignment.domain.repository

import kotlinx.coroutines.flow.Flow
import org.elice.assignment.domain.entities.LectureEntity
import org.elice.assignment.domain.model.ApiResult

interface EliceLectureRepo {
    fun getLectureList(
        offset: Int,
        count: Int,
        courseId: Int
    ): Flow<ApiResult<List<LectureEntity>>>
}