package org.elice.assignment.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "enrolled_courses")
data class EliceEnrolledCourse(
    @PrimaryKey val id: Int
)