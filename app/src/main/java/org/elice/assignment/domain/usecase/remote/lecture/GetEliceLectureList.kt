package org.elice.assignment.domain.usecase.remote.lecture

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.withContext
import org.elice.assignment.domain.entities.LectureEntity
import org.elice.assignment.domain.model.ApiResult
import org.elice.assignment.domain.repository.EliceLectureRepo
import javax.inject.Inject

class GetEliceLectureList @Inject constructor(
    private val eliceLectureeRepo: EliceLectureRepo
) {
    operator fun invoke(
        offset: Int,
        count: Int,
        courseId: Int
    ): Flow<ApiResult<List<LectureEntity>>> = channelFlow {
        val apiFlow = eliceLectureeRepo.getLectureList(offset, count, courseId)

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
