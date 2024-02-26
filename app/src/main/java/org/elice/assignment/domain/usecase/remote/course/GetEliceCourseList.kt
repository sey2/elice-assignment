package org.elice.assignment.domain.usecase.remote.course

import kotlinx.coroutines.flow.Flow
import org.elice.assignment.domain.entities.CourseEntity
import org.elice.assignment.domain.repository.EliceCourseRepo
import javax.inject.Inject

class GetEliceCourseList @Inject constructor(
    private val eliceCourseRepo: EliceCourseRepo
) {
    operator fun invoke(
        offset: Int,
        count: Int,
        filterIsRecommended: Boolean? = null,
        filterIsFree: Boolean? = null,
        filterConditions: String? = null
    ): Flow<List<CourseEntity>> =
        eliceCourseRepo.getCourseList(
            offset, count, filterIsRecommended, filterIsFree, filterConditions
        )

}
