package com.azyabon.habits.ui.screen.today

import androidx.lifecycle.ViewModel
import com.azyabon.habits.ui.util.mockHabits
import com.azyabon.habits.ui.util.mockProgress
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TodayViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(TodayUiState())
    val uiState: StateFlow<TodayUiState> = _uiState.asStateFlow()

    init {
        loadMockHabits()
    }

    private fun loadMockHabits() {
        _uiState.update { currentState ->
            currentState.copy(habits = mockHabits, habitsProgress = mockProgress)
        }
    }
}
