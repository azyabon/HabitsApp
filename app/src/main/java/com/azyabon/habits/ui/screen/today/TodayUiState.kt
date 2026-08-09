package com.azyabon.habits.ui.screen.today

import com.azyabon.habits.domain.model.Habit
import com.azyabon.habits.domain.model.HabitProgress

data class TodayUiState(
    val habits: List<Habit> = emptyList(),
    val habitsProgress: List<HabitProgress> = emptyList(),
)
