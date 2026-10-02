package com.azyabon.habits.ui.screen.habit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HabitViewModel(
    habitId: String,
) : ViewModel() {
    class Factory(
        private val habitId: String,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = HabitViewModel(habitId) as T
    }

    private val _uiState = MutableStateFlow(HabitUiState())
    val uiState: StateFlow<HabitUiState> = _uiState.asStateFlow()

    init {
        loadMockHabit(habitId)
    }

    private fun loadMockHabit(habitId: String) {
        _uiState.update { currentState ->
            currentState.copy()
        }
    }
}
