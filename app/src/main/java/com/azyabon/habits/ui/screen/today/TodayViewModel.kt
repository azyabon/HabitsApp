package com.azyabon.habits.ui.screen.today

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azyabon.habits.data.repository.HabitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodayViewModel
    @Inject
    constructor(
        private val habitRepository: HabitRepository,
    ) : ViewModel() {
        private val _uiState = MutableStateFlow(TodayUiState())
        val uiState: StateFlow<TodayUiState> = _uiState.asStateFlow()

        init {
            loadHabits()
        }

        private fun loadHabits() {
            viewModelScope.launch {
                habitRepository.observeActiveHabits().collect { habits ->
                    _uiState.update { currentState ->
                        currentState.copy(habits = habits)
                    }
                }
            }
        }
    }
