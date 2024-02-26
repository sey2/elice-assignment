package org.elice.assignment.data.di

import org.elice.assignment.database.EliceDatabase
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.elice.assignment.data.api.EliceApiService
import org.elice.assignment.data.model.EliceCourseDao
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

    @Provides
    @Singleton
    fun provideEliceDatabase(@ApplicationContext appContext: Context): EliceDatabase {
        return Room.databaseBuilder(
            appContext,
            EliceDatabase::class.java,
            "elice_database"
        ).build()
    }

    @Provides
    fun provideEliceCourseDao(database: EliceDatabase): EliceCourseDao {
        return database.courseDao()
    }
}