package com.azyabon.habits.domain.model

import java.time.DayOfWeek

data class Habit(
    val id: String,
    val name: String,
    val category: HabitCategory,
    val target: HabitTarget,
    val schedule: HabitSchedule,
    val isActive: Boolean,
)

enum class HabitCategory {
    Water,
    Sport,
    Medicine,
    Study,
    Work,
    Visit,
    Custom,
}

sealed interface HabitSchedule {
    data object EveryDay : HabitSchedule

    data class SpecificWeekDays(
        val days: Set<DayOfWeek>,
    ) : HabitSchedule
}

sealed interface HabitTarget {
    data object CheckOff : HabitTarget

    data class Amount(
        val value: Int,
        val unit: HabitUnit,
    ) : HabitTarget
}

enum class HabitUnit {
    Milliliters,
    Pills,
    Hours,
    Minutes,
    Times,
}

data class HabitProgress(
    val habitId: String,
    val date: String,
    val currentValue: Int = 0,
) {
    fun isDone(target: HabitTarget): Boolean =
        when (target) {
            HabitTarget.CheckOff -> currentValue > 0
            is HabitTarget.Amount -> currentValue >= target.value
        }

    fun getProgressText(target: HabitTarget): String? =
        when (target) {
            HabitTarget.CheckOff -> null
            is HabitTarget.Amount -> "$currentValue / ${target.value} ${target.unit}"
        }
}

fun HabitSchedule.isDueOn(day: DayOfWeek): Boolean =
    when (this) {
        HabitSchedule.EveryDay -> true
        is HabitSchedule.SpecificWeekDays -> day in days
    }
