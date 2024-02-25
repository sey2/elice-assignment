package org.elice.assignment.domain.usecase.course

import kotlinx.coroutines.flow.Flow
import org.elice.assignment.domain.entities.CourseDetailEntity
import org.elice.assignment.domain.repository.EliceCourseRepo
import javax.inject.Inject

class GetEliceCourse @Inject constructor(
    private val eliceCourseRepo: EliceCourseRepo
) {
    operator fun invoke(courseId: Int): Flow<CourseDetailEntity> =
        eliceCourseRepo.getCourse(courseId)

}
