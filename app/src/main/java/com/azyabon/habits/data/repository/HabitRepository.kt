package com.azyabon.habits.data.repository

import com.azyabon.habits.data.local.dao.HabitDao
import com.azyabon.habits.data.local.dao.HabitProgressDao
import com.azyabon.habits.data.local.mapper.toDomain
import com.azyabon.habits.data.local.mapper.toEntity
import com.azyabon.habits.domain.model.Habit
import com.azyabon.habits.domain.model.HabitProgress
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HabitRepository(
    private val habitDao: HabitDao,
    private val progressDao: HabitProgressDao,
) {
    fun observeActiveHabits(): Flow<List<Habit>> =
        habitDao.observeActiveHabits().map {
            it.map { habitEntity -> habitEntity.toDomain() }
        }

    fun observeHabitById(id: String): Flow<Habit?> = habitDao.observeHabitById(id).map { it?.toDomain() }

    suspend fun saveHabit(habit: Habit) {
        habitDao.upsertHabit(habit.toEntity())
    }

    suspend fun deleteHabit(id: String) {
        progressDao.deleteProgressByHabitId(id)
        habitDao.deleteHabitById(id)
    }

    fun observeProgressByDate(date: String): Flow<List<HabitProgress>> =
        progressDao.observeProgressByDate(date).map {
            it.map { progressEntity -> progressEntity.toDomain() }
        }

    suspend fun saveProgress(progress: HabitProgress) {
        progressDao.upsertProgress(progress.toEntity())
    }
}
