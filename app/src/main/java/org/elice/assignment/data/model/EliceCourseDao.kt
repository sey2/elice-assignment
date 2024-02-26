package org.elice.assignment.data.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.elice.assignment.domain.entities.EliceEnrolledCourse

@Dao
interface EliceCourseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourse(enrolledCourse: EliceEnrolledCourse)

    @Query("SELECT id FROM enrolled_courses")
    suspend fun getEnrolledCourses(): List<Int>

    @Query("SELECT EXISTS(SELECT 1 FROM enrolled_courses WHERE id = :courseId)")
    suspend fun isCourseEnrolled(courseId: Int): Boolean
}