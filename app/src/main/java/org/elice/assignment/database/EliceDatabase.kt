package org.elice.assignment.database

import androidx.room.Database
import androidx.room.RoomDatabase
import org.elice.assignment.domain.entities.EliceEnrolledCourse

@Database(entities = [EliceEnrolledCourse::class], version = 1, exportSchema = false)
abstract class EliceDatabase : RoomDatabase() {
    abstract fun courseDao(): EliceCourseDao
}