package com.azyabon.habits.ui.screen.managehabit

import com.azyabon.habits.domain.model.HabitProgressMode
import com.azyabon.habits.domain.model.HabitUnit
import java.time.DayOfWeek
import java.time.LocalDate

data class ManageHabitUiState(
    val name: String = "",
    val description: String = "",
    val color: Long = 0xFF6200EE,
    val progressMode: HabitProgressMode = HabitProgressMode.Complete,
    val goal: String = "1",
    val unit: HabitUnit = HabitUnit.Count,
    val step: String = "1",
    val scheduleType: HabitScheduleType = HabitScheduleType.EveryDay,
    val selectedWeekDays: Set<DayOfWeek> = emptySet(),
    val startDate: String = LocalDate.now().toString(),
    val endDate: String? = null,
    val isEditMode: Boolean = false,
    val nameError: String? = null,
    val goalError: String? = null,
    val stepError: String? = null,
    val selectedWeekDaysError: String? = null,
)
