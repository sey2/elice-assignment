package org.elice.assignment.domain.usecase.lecture

import kotlinx.coroutines.flow.Flow
import org.elice.assignment.domain.entities.LectureEntity
import org.elice.assignment.domain.repository.EliceLectureRepo
import javax.inject.Inject

class GetEliceLectureList @Inject constructor(
    private val eliceLectureeRepo: EliceLectureRepo
) {
    operator fun invoke(
        offset: Int,
        count: Int,
        courseId: Int
    ): Flow<List<LectureEntity>> =
        eliceLectureeRepo.getLectureList(offset, count, courseId)

}
