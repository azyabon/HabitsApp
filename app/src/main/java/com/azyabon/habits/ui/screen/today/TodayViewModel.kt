package com.azyabon.habits.ui.screen.today

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azyabon.habits.data.repository.HabitRepository
import com.azyabon.habits.domain.model.HabitProgress
import com.azyabon.habits.domain.model.isActiveOn
import com.azyabon.habits.domain.model.isDueOn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class TodayViewModel
    @Inject
    constructor(
        private val habitRepository: HabitRepository,
    ) : ViewModel() {
        private val _uiState = MutableStateFlow(TodayUiState())
        val uiState: StateFlow<TodayUiState> = _uiState.asStateFlow()

        private val currentDate = MutableStateFlow(LocalDate.now())

        init {
            getHabits()
        }

        @OptIn(ExperimentalCoroutinesApi::class)
        private fun getHabits() {
            viewModelScope.launch {
                currentDate
                    .flatMapLatest { date ->
                        combine(
                            habitRepository.observeActiveHabits(),
                            habitRepository.observeProgressByDate(date.toString()),
                        ) { habits, progressList ->
                            val progressByHabitId =
                                progressList.associateBy { progress ->
                                    progress.habitId
                                }

                            habits
                                .filter { habit ->
                                    habit.schedule.isDueOn(date.dayOfWeek) && habit.isActiveOn(date)
                                }.map { habit ->
                                    val progress =
                                        progressByHabitId[habit.id]
                                            ?: HabitProgress(
                                                habitId = habit.id,
                                                date = date.toString(),
                                                value = 0,
                                            )

                                    TodayHabitUi(
                                        id = habit.id,
                                        name = habit.name,
                                        isDone = progress.isDone(habit),
                                        progressText = progress.getProgressText(habit),
                                    )
                                }
                        }
                    }.collect { todayHabits ->
                        _uiState.update { currentState ->
                            currentState.copy(habits = todayHabits)
                        }
                    }
            }
        }

        fun onProgressClick(habitId: String) {
            viewModelScope.launch {
                habitRepository.updateProgress(
                    habitId = habitId,
                    date = currentDate.value.toString(),
                )
            }
        }
    }
