package com.azyabon.habits.ui.util

import com.azyabon.habits.domain.model.Habit
import com.azyabon.habits.domain.model.HabitCategory
import com.azyabon.habits.domain.model.HabitProgress
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
            isActive = true,
        ),
        Habit(
            id = "fitness",
            name = "Do Fitness",
            category = HabitCategory.Fitness,
            target =
                HabitTarget.Amount(
                    value = 30,
                    unit = HabitUnit.Minutes,
                ),
            isActive = true,
        ),
        Habit(
            id = "reading",
            name = "Read book",
            category = HabitCategory.Reading,
            target = HabitTarget.CheckOff,
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
            habitId = "fitness",
            date = LocalDate.now().toString(),
            currentValue = 21,
        ),
        HabitProgress(
            habitId = "reading",
            date = LocalDate.now().toString(),
        ),
    )
