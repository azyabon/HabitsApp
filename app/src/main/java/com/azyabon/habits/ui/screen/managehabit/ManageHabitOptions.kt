package com.azyabon.habits.ui.screen.managehabit

import com.azyabon.habits.core.designsystem.component.Option
import com.azyabon.habits.domain.model.HabitCategory
import com.azyabon.habits.domain.model.HabitUnit
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.Locale

internal val categoryOptions =
    listOf(
        Option(HabitCategory.Water, "Water"),
        Option(HabitCategory.Sport, "Sport"),
        Option(HabitCategory.Medicine, "Medicine"),
        Option(HabitCategory.Study, "Study"),
        Option(HabitCategory.Work, "Work"),
        Option(HabitCategory.Visit, "Visit"),
        Option(HabitCategory.Custom, "Custom"),
    )

internal val targetTypeOptions =
    listOf(
        Option(HabitTargetType.CheckOff, "Check off"),
        Option(HabitTargetType.Amount, "Amount"),
    )

internal val unitOptions =
    listOf(
        Option(HabitUnit.Milliliters, "Milliliters"),
        Option(HabitUnit.Pills, "Pills"),
        Option(HabitUnit.Hours, "Hours"),
        Option(HabitUnit.Minutes, "Minutes"),
        Option(HabitUnit.Times, "Times"),
    )

internal val scheduleTypeOptions =
    listOf(
        Option(HabitScheduleType.EveryDay, "Every day"),
        Option(HabitScheduleType.SpecificWeekDays, "Specific week days"),
    )

internal val daysOptions =
    DayOfWeek.entries.map { day ->
        Option(
            value = day,
            label = day.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
        )
    }
