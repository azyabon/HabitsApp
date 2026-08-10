package com.azyabon.habits.ui.screen.managehabit

import com.azyabon.habits.core.designsystem.component.Option
import com.azyabon.habits.domain.model.HabitCategory
import com.azyabon.habits.domain.model.HabitUnit

val categoryOptions =
    listOf(
        Option(HabitCategory.Water, "Water"),
        Option(HabitCategory.Sport, "Sport"),
        Option(HabitCategory.Medicine, "Medicine"),
        Option(HabitCategory.Study, "Study"),
        Option(HabitCategory.Work, "Work"),
        Option(HabitCategory.Visit, "Visit"),
        Option(HabitCategory.Custom, "Custom"),
    )

val targetTypeOptions =
    listOf(
        Option(HabitTargetType.CheckOff, "Check off"),
        Option(HabitTargetType.Amount, "Amount"),
    )

val unitOptions =
    listOf(
        Option(HabitUnit.Milliliters, "Milliliters"),
        Option(HabitUnit.Pills, "Pills"),
        Option(HabitUnit.Hours, "Hours"),
        Option(HabitUnit.Minutes, "Minutes"),
        Option(HabitUnit.Times, "Times"),
    )

val scheduleTypeOptions =
    listOf(
        Option(HabitScheduleType.EveryDay, "Every day"),
        Option(HabitScheduleType.SpecificWeekDays, "Specific week days"),
    )
