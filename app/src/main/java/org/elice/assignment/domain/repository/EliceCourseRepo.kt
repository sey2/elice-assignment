package org.elice.assignment.domain.repository

import kotlinx.coroutines.flow.Flow
import org.elice.assignment.domain.entities.CourseEntity

interface EliceCourseRepo {
    fun getCourseList(
        offset: Int,
        count: Int,
        filterIsRecommended: Boolean? = null,
        filterIsFree: Boolean? = null,
        filterConditions: String? = null
    ): Flow<List<CourseEntity>>
}