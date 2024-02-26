package org.elice.assignment.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.elice.assignment.data.source.EliceLectureDataSource
import org.elice.assignment.domain.entities.LectureEntity
import org.elice.assignment.domain.repository.EliceLectureRepo
import javax.inject.Inject

class EliceLectureRepoImpl @Inject constructor(
    private val eliceLectureDataSource: EliceLectureDataSource
) : EliceLectureRepo {
    override fun getLectureList(
        offset: Int,
        count: Int,
        courseId: Int
    ): Flow<List<LectureEntity>> = flow {
        emit(
            eliceLectureDataSource.getLectureList(
                offset,
                count,
                courseId
            )
        )
    }
}

