package org.elice.assignment.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.elice.assignment.data.repository.EliceCourseRepoImpl
import org.elice.assignment.data.repository.EliceLectureRepoImpl
import org.elice.assignment.domain.repository.EliceCourseRepo
import org.elice.assignment.domain.repository.EliceLectureRepo
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    @Singleton
    fun bindEliceCourseRepository(
        eliceCourseRepo: EliceCourseRepoImpl
    ): EliceCourseRepo

    @Binds
    @Singleton
    fun bindEliceLectureRepository(
        eliceLectureRepo: EliceLectureRepoImpl
    ): EliceLectureRepo
}