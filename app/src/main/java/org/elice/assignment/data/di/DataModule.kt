package org.elice.assignment.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.elice.assignment.data.repository.EliceCourseRepoIml
import org.elice.assignment.domain.repository.EliceCourseRepo
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    @Singleton
    fun bindEliceCourseRepository(
        eliceCourseRepo: EliceCourseRepoIml
    ): EliceCourseRepo
}