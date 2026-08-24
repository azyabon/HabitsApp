package com.azyabon.habits.ui.screen.managehabit

import com.azyabon.habits.domain.model.HabitProgressMode
import com.azyabon.habits.domain.model.HabitUnit
import java.time.DayOfWeek

data class ManageHabitUiState(
    val name: String = "",
    val progressMode: HabitProgressMode = HabitProgressMode.Complete,
    val goal: String = "1",
    val unit: HabitUnit = HabitUnit.Count,
    val step: String = "1",
    val isEditMode: Boolean = false,
    val nameError: String? = null,
    val goalError: String? = null,
    val stepError: String? = null,
    val scheduleType: HabitScheduleType = HabitScheduleType.EveryDay,
    val selectedWeekDays: Set<DayOfWeek> = emptySet(),
    val selectedWeekDaysError: String? = null,
)
