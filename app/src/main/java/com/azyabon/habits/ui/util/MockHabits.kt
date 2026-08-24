package com.azyabon.habits.ui.util

import com.azyabon.habits.domain.model.Habit
import com.azyabon.habits.domain.model.HabitProgress
import com.azyabon.habits.domain.model.HabitProgressMode
import com.azyabon.habits.domain.model.HabitSchedule
import com.azyabon.habits.domain.model.HabitUnit
import java.time.LocalDate

val mockHabits: List<Habit> =
    listOf(
        Habit(
            id = "water",
            name = "Drink water",
            goal = 2000,
            unit = HabitUnit.Milliliters,
            progressMode = HabitProgressMode.AddValue,
            step = 10,
            schedule = HabitSchedule.EveryDay,
            isActive = true,
        ),
        Habit(
            id = "sport",
            name = "Do Fitness",
            goal = 30,
            unit = HabitUnit.Minutes,
            progressMode = HabitProgressMode.AddValue,
            step = 10,
            schedule = HabitSchedule.EveryDay,
            isActive = true,
        ),
        Habit(
            id = "study",
            name = "Read book",
            goal = 1,
            unit = HabitUnit.Times,
            progressMode = HabitProgressMode.Complete,
            step = 1,
            schedule = HabitSchedule.EveryDay,
            isActive = true,
        ),
    )

val mockProgress: List<HabitProgress> =
    listOf(
        HabitProgress(
            habitId = "water",
            date = LocalDate.now().toString(),
            value = 2000,
        ),
        HabitProgress(
            habitId = "sport",
            date = LocalDate.now().toString(),
            value = 21,
        ),
        HabitProgress(
            habitId = "study",
            date = LocalDate.now().toString(),
        ),
    )
