package org.elice.assignment.data.source

import org.elice.assignment.data.api.EliceApiService
import org.elice.assignment.database.EliceCourseDao
import org.elice.assignment.domain.entities.EliceEnrolledCourse
import javax.inject.Inject

class EliceCourseDataSource @Inject constructor(
    private val eliceCourseService: EliceApiService,
    private val courseDao: EliceCourseDao
) {
    suspend fun getCourseList(
        offset: Int,
        count: Int,
        filterIsRecommended: Boolean? = null,
        filterIsFree: Boolean? = null,
        filterConditions: String? = null
    ) = eliceCourseService.getCourseList(
        offset,
        count,
        filterIsRecommended,
        filterIsFree,
        filterConditions
    ).courses

    suspend fun getCourse(
        courseId: Int
    ) = eliceCourseService.getCourse(courseId).courseDetail

    suspend fun enrolledCourse(courseId: Int)
        = courseDao.insertCourse(EliceEnrolledCourse(courseId))


    suspend fun getEnrolledCourses()
        = courseDao.getEnrolledCourses()

    suspend fun isEnrolledCourse(courseId: Int)
        = courseDao.isCourseEnrolled(courseId)

    suspend fun deleteEnrolledCourse(courseId: Int)
        = courseDao.deleteCourse(courseId)
}