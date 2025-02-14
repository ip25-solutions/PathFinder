package com.ipsolutions.pathfinder.data

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Goal::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun goalDao(): GoalDao
}