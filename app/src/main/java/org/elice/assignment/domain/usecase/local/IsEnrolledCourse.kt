package org.elice.assignment.domain.usecase.local

import kotlinx.coroutines.flow.Flow
import org.elice.assignment.domain.repository.EliceCourseRepo
import javax.inject.Inject

class IsEnrolledCourse@Inject constructor(
    private val eliceCourseRepo: EliceCourseRepo
)  {
    suspend operator fun invoke(courseId: Int): Flow<Boolean>
            = eliceCourseRepo.isEnrolledCourse(courseId)
}