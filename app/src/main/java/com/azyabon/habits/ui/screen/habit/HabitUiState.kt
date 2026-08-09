package com.azyabon.habits.ui.screen.habit

import com.azyabon.habits.domain.model.Habit
import com.azyabon.habits.domain.model.HabitProgress

data class HabitUiState(
    val habit: Habit? = null,
    val habitProgress: HabitProgress? = null,
)
