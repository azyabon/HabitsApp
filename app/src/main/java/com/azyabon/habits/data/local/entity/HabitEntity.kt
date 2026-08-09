package com.azyabon.habits.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habits")
data class HabitEntity(
    @PrimaryKey val id: String,
    val name: String,
    val category: String,
    val targetType: String,
    val targetAmount: Int?,
    val targetUnit: String?,
    val scheduleType: String,
    val scheduleDays: String?,
    val isActive: Boolean,
)
