package org.elice.assignment.domain.repository

import kotlinx.coroutines.flow.Flow
import org.elice.assignment.domain.entities.CourseDetailEntity
import org.elice.assignment.domain.entities.CourseEntity
import org.elice.assignment.domain.model.ApiResult

interface EliceCourseRepo {
    fun getCourseList(
        offset: Int,
        count: Int,
        filterIsRecommended: Boolean? = null,
        filterIsFree: Boolean? = null,
        filterConditions: String? = null
    ): Flow<ApiResult<List<CourseEntity>>>

    fun getCourse(courseId: Int): Flow<ApiResult<CourseDetailEntity>>

    suspend fun enrollCourse(courseId: Int)

    suspend fun getEnrollCourse(): Flow<List<Int>>

    suspend fun isEnrolledCourse(courseId: Int): Flow<Boolean>

    suspend fun deleteEnrollCourse(courseId: Int)
}