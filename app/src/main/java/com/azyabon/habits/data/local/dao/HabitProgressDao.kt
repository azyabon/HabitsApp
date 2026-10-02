package com.azyabon.habits.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.azyabon.habits.data.local.entity.HabitProgressEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitProgressDao {
    @Query("SELECT * FROM habit_progress WHERE date = :date")
    fun observeProgressByDate(date: String): Flow<List<HabitProgressEntity>>

    @Query("SELECT * FROM habit_progress WHERE habitId = :habitId AND date = :date")
    fun observeProgressForHabitByDate(
        habitId: String,
        date: String,
    ): Flow<HabitProgressEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertProgress(progress: HabitProgressEntity)

    @Query("DELETE FROM habit_progress WHERE habitId = :id")
    suspend fun deleteProgressByHabitId(id: String)

    @Query("SELECT * FROM habit_progress WHERE habitId = :habitId AND date = :date")
    suspend fun getProgressForHabitByDate(
        habitId: String,
        date: String,
    ): HabitProgressEntity?
}
