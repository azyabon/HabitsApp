package com.azyabon.habits.ui.screen.managehabit

import com.azyabon.habits.core.designsystem.component.Option
import com.azyabon.habits.domain.model.HabitProgressMode
import com.azyabon.habits.domain.model.HabitUnit
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.Locale

internal val progressModeOptions =
    listOf(
        Option(HabitProgressMode.Complete, "Mark as Complete"),
        Option(HabitProgressMode.AddValue, "Add Value"),
    )

internal val unitOptions =
    listOf(
        Option(HabitUnit.Milliliters, "Milliliters"),
        Option(HabitUnit.Pills, "Pills"),
        Option(HabitUnit.Hours, "Hours"),
        Option(HabitUnit.Minutes, "Minutes"),
        Option(HabitUnit.Times, "Times"),
        Option(HabitUnit.Count, "Count"),
        Option(HabitUnit.Steps, "Steps"),
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
