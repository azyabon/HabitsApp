package com.azyabon.habits.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.azyabon.habits.data.local.dao.HabitDao
import com.azyabon.habits.data.local.dao.HabitProgressDao
import com.azyabon.habits.data.local.entity.HabitEntity
import com.azyabon.habits.data.local.entity.HabitProgressEntity

@Database(
    entities = [
        HabitEntity::class,
        HabitProgressEntity::class,
    ],
    version = 1,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun habitDao(): HabitDao

    abstract fun habitProgressDao(): HabitProgressDao
}
