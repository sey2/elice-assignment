package org.elice.assignment.domain.repository

import kotlinx.coroutines.flow.Flow
import org.elice.assignment.domain.entities.LectureEntity

interface EliceLectureRepo {
    fun getLectureList(
        offset: Int,
        count: Int,
        courseId: Int
    ): Flow<List<LectureEntity>>
}