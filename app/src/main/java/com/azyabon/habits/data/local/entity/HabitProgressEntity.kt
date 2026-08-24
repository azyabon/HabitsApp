package com.azyabon.habits.data.local.entity

import androidx.room.Entity

@Entity(
    tableName = "habit_progress",
    primaryKeys = ["habitId", "date"],
)
data class HabitProgressEntity(
    val habitId: String,
    val date: String,
    val value: Int,
)
