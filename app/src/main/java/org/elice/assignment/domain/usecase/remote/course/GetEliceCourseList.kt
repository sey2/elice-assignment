package org.elice.assignment.domain.usecase.remote.course

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.withContext
import org.elice.assignment.domain.entities.CourseEntity
import org.elice.assignment.domain.model.ApiResult
import org.elice.assignment.domain.repository.EliceCourseRepo
import javax.inject.Inject


class GetEliceCourseList @Inject constructor(
    private val eliceCourseRepo: EliceCourseRepo
) {
    operator fun invoke(
        offset: Int,
        count: Int,
        filterIsRecommended: Boolean? = null,
        filterIsFree: Boolean? = null,
        filterConditions: String? = null
    ): Flow<ApiResult<List<CourseEntity>>> = channelFlow {
        val apiFlow = eliceCourseRepo.getCourseList(
            offset, count, filterIsRecommended, filterIsFree, filterConditions
        )

        withContext(Dispatchers.IO) {
            apiFlow.collectLatest { apiResult ->
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
}
