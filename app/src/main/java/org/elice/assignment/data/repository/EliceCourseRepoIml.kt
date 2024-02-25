package org.elice.assignment.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.elice.assignment.data.source.EliceCourseDataSource
import org.elice.assignment.domain.entities.CourseDetailEntity
import org.elice.assignment.domain.entities.CourseEntity
import org.elice.assignment.domain.repository.EliceCourseRepo
import javax.inject.Inject

class EliceCourseRepoIml @Inject constructor(
    private val eliceCourseDataSource: EliceCourseDataSource
) : EliceCourseRepo {

    override fun getCourseList(
        offset: Int,
        count: Int,
        filterIsRecommended: Boolean?,
        filterIsFree: Boolean?,
        filterConditions: String?
    ): Flow<List<CourseEntity>> = flow {
        emit(
            eliceCourseDataSource.getCourseList(
                offset,
                count,
                filterIsRecommended,
                filterIsFree,
                filterConditions
            )
        )
    }

    override fun getCourse(courseId: Int): Flow<CourseDetailEntity> = flow{
        emit(
            eliceCourseDataSource.getCourse(courseId)
        )
    }
}