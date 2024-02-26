package org.elice.assignment.domain.usecase.local

import org.elice.assignment.domain.repository.EliceCourseRepo
import javax.inject.Inject

class AddEnrollCourse @Inject constructor(
    private val eliceCourseRepo: EliceCourseRepo
) {
    suspend operator fun invoke(courseId: Int)
        = eliceCourseRepo.enrollCourse(courseId)
}