package com.azyabon.habits.di

import android.app.Application
import androidx.room.Room
import com.azyabon.habits.data.local.AppDatabase
import com.azyabon.habits.data.local.dao.HabitDao
import com.azyabon.habits.data.local.dao.HabitProgressDao
import com.azyabon.habits.data.repository.HabitRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase =
        Room
            .databaseBuilder(
                application,
                AppDatabase::class.java,
                "app_database",
            ).build()

    @Provides
    @Singleton
    fun provideHabitDao(appDatabase: AppDatabase) = appDatabase.habitDao()

    @Provides
    @Singleton
    fun provideHabitProgressDao(appDatabase: AppDatabase) = appDatabase.habitProgressDao()

    @Provides
    @Singleton
    fun provideHabitRepository(
        habitDao: HabitDao,
        habitProgressDao: HabitProgressDao,
    ): HabitRepository = HabitRepository(habitDao, habitProgressDao)
}
