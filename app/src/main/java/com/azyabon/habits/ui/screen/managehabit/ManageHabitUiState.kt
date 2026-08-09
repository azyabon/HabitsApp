package com.azyabon.habits.ui.screen.managehabit

import com.azyabon.habits.domain.model.HabitCategory
import com.azyabon.habits.domain.model.HabitUnit
import java.time.DayOfWeek

data class ManageHabitUiState(
    val name: String = "",
    val category: HabitCategory? = null,
    val targetType: HabitTargetType? = null,
    val amount: String = "",
    val unit: HabitUnit? = null,
    val isEditMode: Boolean = false,
    val nameError: String? = null,
    val amountError: String? = null,
    val categoryError: String? = null,
    val targetTypeError: String? = null,
    val unitError: String? = null,
    val scheduleType: HabitScheduleType = HabitScheduleType.EveryDay,
    val selectedWeekDays: Set<DayOfWeek> = emptySet(),
    val selectedWeekDaysError: String? = null,
)
