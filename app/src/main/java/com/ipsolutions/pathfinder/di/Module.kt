package com.ipsolutions.pathfinder.di

import android.content.Context
import androidx.room.Room
import com.ipsolutions.pathfinder.data.AppDatabase
import com.ipsolutions.pathfinder.data.GoalDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "goal_database"
        ).build()

    @Provides
    fun provideGoalDao(database: AppDatabase): GoalDao = database.goalDao()
}