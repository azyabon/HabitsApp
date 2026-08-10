package com.azyabon.habits.ui.util

import com.azyabon.habits.domain.model.Habit
import com.azyabon.habits.domain.model.HabitCategory
import com.azyabon.habits.domain.model.HabitProgress
import com.azyabon.habits.domain.model.HabitSchedule
import com.azyabon.habits.domain.model.HabitTarget
import com.azyabon.habits.domain.model.HabitUnit
import java.time.LocalDate

val mockHabits: List<Habit> =
    listOf(
        Habit(
            id = "water",
            name = "Drink water",
            category = HabitCategory.Water,
            target =
                HabitTarget.Amount(
                    value = 2000,
                    unit = HabitUnit.Milliliters,
                ),
            schedule = HabitSchedule.EveryDay,
            isActive = true,
        ),
        Habit(
            id = "sport",
            name = "Do Fitness",
            category = HabitCategory.Sport,
            target =
                HabitTarget.Amount(
                    value = 30,
                    unit = HabitUnit.Minutes,
                ),
            schedule = HabitSchedule.EveryDay,
            isActive = true,
        ),
        Habit(
            id = "study",
            name = "Read book",
            category = HabitCategory.Study,
            target = HabitTarget.CheckOff,
            schedule = HabitSchedule.EveryDay,
            isActive = true,
        ),
    )

val mockProgress: List<HabitProgress> =
    listOf(
        HabitProgress(
            habitId = "water",
            date = LocalDate.now().toString(),
            currentValue = 2000,
        ),
        HabitProgress(
            habitId = "sport",
            date = LocalDate.now().toString(),
            currentValue = 21,
        ),
        HabitProgress(
            habitId = "study",
            date = LocalDate.now().toString(),
        ),
    )
