package org.elice.assignment.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.elice.assignment.data.mapper.EliceCourseListMapper.toDomain
import org.elice.assignment.data.source.EliceCourseDataSource
import org.elice.assignment.data.util.safeFlow
import org.elice.assignment.domain.entities.CourseDetailEntity
import org.elice.assignment.domain.entities.CourseEntity
import org.elice.assignment.domain.model.ApiResult
import org.elice.assignment.domain.repository.EliceCourseRepo
import javax.inject.Inject

class EliceCourseRepoImpl @Inject constructor(
    private val eliceCourseDataSource: EliceCourseDataSource
) : EliceCourseRepo {

    override fun getCourseList(
        offset: Int,
        count: Int,
        filterIsRecommended: Boolean?,
        filterIsFree: Boolean?,
        filterConditions: String?
    ): Flow<ApiResult<List<CourseEntity>>> = safeFlow {
        eliceCourseDataSource.getCourseList(
            offset,
            count,
            filterIsRecommended,
            filterIsFree,
            filterConditions
        ).toDomain()
    }

    override fun getCourse(courseId: Int): Flow<CourseDetailEntity> = flow {
        emit(
            eliceCourseDataSource.getCourse(courseId)
        )
    }

    override suspend fun enrollCourse(courseId: Int) =
        eliceCourseDataSource.enrolledCourse(courseId)


    override suspend fun getEnrollCourse(): Flow<List<Int>> = flow {
        emit(
            eliceCourseDataSource.getEnrolledCourses()
        )
    }

    override suspend fun isEnrolledCourse(courseId: Int): Flow<Boolean> = flow {
        emit(
            eliceCourseDataSource.isEnrolledCourse(courseId)
        )
    }

    override suspend fun deleteEnrollCourse(courseId: Int) =
        eliceCourseDataSource.deleteEnrolledCourse(courseId)

}