package com.azyabon.habits.data.local.mapper

import com.azyabon.habits.data.local.entity.HabitEntity
import com.azyabon.habits.data.local.entity.HabitProgressEntity
import com.azyabon.habits.domain.model.Habit
import com.azyabon.habits.domain.model.HabitProgress
import com.azyabon.habits.domain.model.HabitProgressMode
import com.azyabon.habits.domain.model.HabitSchedule
import com.azyabon.habits.domain.model.HabitUnit
import java.time.DayOfWeek

private const val PROGRESS_MODE_COMPLETE = "COMPLETE"
private const val PROGRESS_MODE_ADD_VALUE = "ADD_VALUE"

private const val SCHEDULE_TYPE_EVERY_DAY = "EVERY_DAY"
private const val SCHEDULE_TYPE_SPECIFIC_WEEK_DAYS = "SPECIFIC_WEEK_DAYS"

fun Habit.toEntity() =
    HabitEntity(
        id = id,
        name = name,
        goal = goal,
        unit = unit.name,
        progressMode =
            when (progressMode) {
                HabitProgressMode.Complete -> PROGRESS_MODE_COMPLETE
                HabitProgressMode.AddValue -> PROGRESS_MODE_ADD_VALUE
            },
        step = step,
        scheduleType =
            when (schedule) {
                HabitSchedule.EveryDay -> SCHEDULE_TYPE_EVERY_DAY
                is HabitSchedule.SpecificWeekDays -> SCHEDULE_TYPE_SPECIFIC_WEEK_DAYS
            },
        scheduleDays =
            when (schedule) {
                HabitSchedule.EveryDay -> {
                    null
                }

                is HabitSchedule.SpecificWeekDays -> {
                    schedule.days.joinToString(",") { day -> day.name }
                }
            },
        isActive = isActive,
    )

fun HabitEntity.toDomain() =
    Habit(
        id = id,
        name = name,
        goal = goal,
        unit = HabitUnit.valueOf(unit),
        progressMode =
            when (progressMode) {
                PROGRESS_MODE_COMPLETE -> {
                    HabitProgressMode.Complete
                }

                PROGRESS_MODE_ADD_VALUE -> {
                    HabitProgressMode.AddValue
                }

                else -> {
                    throw IllegalArgumentException("Unknown target type: $progressMode")
                }
            },
        step = step,
        schedule =
            when (scheduleType) {
                SCHEDULE_TYPE_EVERY_DAY -> {
                    HabitSchedule.EveryDay
                }

                SCHEDULE_TYPE_SPECIFIC_WEEK_DAYS -> {
                    val days =
                        requireNotNull(scheduleDays)
                            .split(",")
                            .map { dayName -> DayOfWeek.valueOf(dayName) }
                            .toSet()

                    HabitSchedule.SpecificWeekDays(days = days)
                }

                else -> {
                    throw IllegalArgumentException("Unknown schedule type: $scheduleType")
                }
            },
        isActive = isActive,
    )

fun HabitProgress.toEntity() =
    HabitProgressEntity(
        habitId = habitId,
        date = date,
        value = value,
    )

fun HabitProgressEntity.toDomain() =
    HabitProgress(
        habitId = habitId,
        date = date,
        value = value,
    )
