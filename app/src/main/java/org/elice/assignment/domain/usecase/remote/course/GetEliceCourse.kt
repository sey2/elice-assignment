package org.elice.assignment.domain.usecase.remote.course

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import org.elice.assignment.domain.entities.CourseDetailEntity
import org.elice.assignment.domain.model.ApiResult
import org.elice.assignment.domain.repository.EliceCourseRepo
import javax.inject.Inject

class GetEliceCourse @Inject constructor(
    private val eliceCourseRepo: EliceCourseRepo
) {
    operator fun invoke(courseId: Int): Flow<ApiResult<CourseDetailEntity>> = channelFlow {
        eliceCourseRepo.getCourse(courseId).collectLatest { apiResult ->
            if (apiResult is ApiResult.Success) {
                send(ApiResult.Success(apiResult.value))
            } else {
                if (apiResult is ApiResult.Error) {
                    send(apiResult)
                } else if (apiResult is ApiResult.Exception) {
                    send(apiResult)
                }
            }
        }
    }
}
