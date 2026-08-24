package com.azyabon.habits.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habits")
data class HabitEntity(
    @PrimaryKey val id: String,
    val name: String,
    val goal: Int,
    val unit: String,
    val progressMode: String,
    val step: Int,
    val scheduleType: String,
    val scheduleDays: String?,
    val isActive: Boolean,
)
