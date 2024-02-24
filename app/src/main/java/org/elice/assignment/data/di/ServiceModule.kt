package org.elice.assignment.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.elice.assignment.data.api.EliceApiService
import org.elice.assignment.network.NetworkType
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ServiceModule {
    @Provides
    @Singleton
    fun provideEliceApiService(
        @Named(NetworkType.ELICE) retrofit: Retrofit
    ) = retrofit.create<EliceApiService>()
}