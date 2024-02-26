package org.elice.assignment.data.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import org.elice.assignment.domain.entities.EliceEnrolledCourse

@Dao
interface EliceCourseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourse(enrolledCourse: EliceEnrolledCourse)
}