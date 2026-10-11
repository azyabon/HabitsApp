package com.azyabon.habits.ui.screen.today

import java.time.LocalDate

data class TodayUiState(
    val habits: List<TodayHabitUi> = emptyList(),
    val currentDate: LocalDate = LocalDate.now(),
)

data class TodayHabitUi(
    val id: String,
    val name: String,
    val isDone: Boolean,
    val progressText: String?,
)
