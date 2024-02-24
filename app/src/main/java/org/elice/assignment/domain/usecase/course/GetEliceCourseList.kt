package org.elice.assignment.domain.usecase.course

import kotlinx.coroutines.flow.Flow
import org.elice.assignment.data.api.EliceApiService
import org.elice.assignment.data.repository.EliceCourseRepoIml
import org.elice.assignment.data.source.EliceCourseDataSource
import org.elice.assignment.domain.entities.CourseEntity
import org.elice.assignment.domain.repository.EliceCourseRepo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GetEliceCourseList(
    private val eliceCourseRepo: EliceCourseRepo = EliceCourseRepoIml(
        EliceCourseDataSource(
            Retrofit.Builder()
                .baseUrl("https://api-rest.elice.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(EliceApiService::class.java)
        )
    )
) {
    operator fun invoke(
        offset: Int,
        count: Int,
        filterIsRecommended: Boolean? = null,
        filterIsFree: Boolean? = null,
        filterConditions: String? = null
    ): Flow<List<CourseEntity>> =
        eliceCourseRepo.getCourseList(
            offset, count, filterIsRecommended, filterIsFree, filterConditions
        )

}
