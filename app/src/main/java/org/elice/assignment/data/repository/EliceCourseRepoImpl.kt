package org.elice.assignment.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.elice.assignment.data.mapper.EliceCourseListMapper.toDomain
import org.elice.assignment.data.source.remote.EliceCourseRemoteSource
import org.elice.assignment.data.source.local.EliceCourseLocalSource
import org.elice.assignment.data.util.safeFlow
import org.elice.assignment.domain.entities.CourseDetailEntity
import org.elice.assignment.domain.entities.CourseEntity
import org.elice.assignment.domain.model.ApiResult
import org.elice.assignment.domain.repository.EliceCourseRepo
import javax.inject.Inject

class EliceCourseRepoImpl @Inject constructor(
    private val eliceCourseRemoteSource: EliceCourseRemoteSource,
    private val eliceCourseLocalSource: EliceCourseLocalSource
) : EliceCourseRepo {

    override fun getCourseList(
        offset: Int,
        count: Int,
        filterIsRecommended: Boolean?,
        filterIsFree: Boolean?,
        filterConditions: String?
    ): Flow<ApiResult<List<CourseEntity>>> = safeFlow {
        eliceCourseRemoteSource.getCourseList(
            offset,
            count,
            filterIsRecommended,
            filterIsFree,
            filterConditions
        ).toDomain()
    }

    override fun getCourse(courseId: Int): Flow<ApiResult<CourseDetailEntity>> = safeFlow {
        eliceCourseRemoteSource.getCourse(courseId)
    }

    override suspend fun enrollCourse(courseId: Int) =
        eliceCourseLocalSource.enrolledCourse(courseId)


    override suspend fun getEnrollCourse(): Flow<List<Int>> = flow {
        emit(
            eliceCourseLocalSource.getEnrolledCourses()
        )
    }

    override suspend fun isEnrolledCourse(courseId: Int): Flow<Boolean> = flow {
        emit(
            eliceCourseLocalSource.isEnrolledCourse(courseId)
        )
    }

    override suspend fun deleteEnrollCourse(courseId: Int) =
        eliceCourseLocalSource.deleteEnrolledCourse(courseId)

}