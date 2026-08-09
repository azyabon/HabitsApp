package com.azyabon.habits.ui.screen.today

data class TodayUiState(
    val habits: List<TodayHabitUi> = emptyList(),
)

data class TodayHabitUi(
    val id: String,
    val name: String,
    val isDone: Boolean,
    val progressText: String?,
)
