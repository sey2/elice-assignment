package org.elice.assignment.data.source.local

import org.elice.assignment.domain.database.EliceCourseDao
import org.elice.assignment.domain.entities.EliceEnrolledCourse
import javax.inject.Inject

class EliceCourseLocalSource @Inject constructor(
    private val courseDao: EliceCourseDao
) {

    suspend fun enrolledCourse(courseId: Int)
            = courseDao.insertCourse(EliceEnrolledCourse(courseId))


    suspend fun getEnrolledCourses()
            = courseDao.getEnrolledCourses()

    suspend fun isEnrolledCourse(courseId: Int)
            = courseDao.isCourseEnrolled(courseId)

    suspend fun deleteEnrolledCourse(courseId: Int)
            = courseDao.deleteCourse(courseId)
}