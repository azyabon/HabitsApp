package com.azyabon.habits.data.local.mapper

import com.azyabon.habits.data.local.entity.HabitEntity
import com.azyabon.habits.data.local.entity.HabitProgressEntity
import com.azyabon.habits.domain.model.Habit
import com.azyabon.habits.domain.model.HabitCategory
import com.azyabon.habits.domain.model.HabitProgress
import com.azyabon.habits.domain.model.HabitSchedule
import com.azyabon.habits.domain.model.HabitTarget
import com.azyabon.habits.domain.model.HabitUnit
import java.time.DayOfWeek

private const val TARGET_TYPE_CHECK_OFF = "CHECK_OFF"
private const val TARGET_TYPE_AMOUNT = "AMOUNT"

private const val SCHEDULE_TYPE_EVERY_DAY = "EVERY_DAY"
private const val SCHEDULE_TYPE_SPECIFIC_WEEK_DAYS = "SPECIFIC_WEEK_DAYS"

fun Habit.toEntity() =
    HabitEntity(
        id = id,
        name = name,
        category = category.name,
        targetType =
            when (target) {
                HabitTarget.CheckOff -> TARGET_TYPE_CHECK_OFF
                is HabitTarget.Amount -> TARGET_TYPE_AMOUNT
            },
        targetAmount = (target as? HabitTarget.Amount)?.value,
        targetUnit = (target as? HabitTarget.Amount)?.unit?.name,
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
        category = HabitCategory.valueOf(category),
        target =
            when (targetType) {
                TARGET_TYPE_CHECK_OFF -> {
                    HabitTarget.CheckOff
                }

                TARGET_TYPE_AMOUNT -> {
                    HabitTarget.Amount(
                        value = requireNotNull(targetAmount),
                        unit = HabitUnit.valueOf(requireNotNull(targetUnit)),
                    )
                }

                else -> {
                    throw IllegalArgumentException("Unknown target type: $targetType")
                }
            },
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
        currentValue = currentValue,
    )

fun HabitProgressEntity.toDomain() =
    HabitProgress(
        habitId = habitId,
        date = date,
        currentValue = currentValue,
    )
