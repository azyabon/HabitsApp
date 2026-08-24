package com.azyabon.habits.domain.model

import java.time.DayOfWeek

data class Habit(
    val id: String,
    val name: String,
    val goal: Int,
    val unit: HabitUnit,
    val progressMode: HabitProgressMode,
    val step: Int,
    val schedule: HabitSchedule,
    val isActive: Boolean,
)

sealed interface HabitSchedule {
    data object EveryDay : HabitSchedule

    data class SpecificWeekDays(
        val days: Set<DayOfWeek>,
    ) : HabitSchedule
}

enum class HabitProgressMode {
    Complete,
    AddValue,
}

enum class HabitUnit {
    Milliliters,
    Pills,
    Hours,
    Minutes,
    Times,
    Count,
    Steps,
}

data class HabitProgress(
    val habitId: String,
    val date: String,
    val value: Int = 0,
) {
    fun isDone(habit: Habit): Boolean = value >= habit.goal

    fun getProgressText(habit: Habit): String = "$value / ${habit.goal} ${habit.unit}"
}

fun HabitSchedule.isDueOn(day: DayOfWeek): Boolean =
    when (this) {
        HabitSchedule.EveryDay -> true
        is HabitSchedule.SpecificWeekDays -> day in days
    }
