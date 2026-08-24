package com.azyabon.habits.ui.screen.managehabit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azyabon.habits.data.repository.HabitRepository
import com.azyabon.habits.domain.model.Habit
import com.azyabon.habits.domain.model.HabitProgressMode
import com.azyabon.habits.domain.model.HabitSchedule
import com.azyabon.habits.domain.model.HabitUnit
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.util.UUID

@HiltViewModel(assistedFactory = ManageHabitViewModel.Factory::class)
class ManageHabitViewModel
    @AssistedInject
    constructor(
        @Assisted private val habitId: String?,
        private val habitRepository: HabitRepository,
    ) : ViewModel() {
        @AssistedFactory
        interface Factory {
            fun create(habitId: String?): ManageHabitViewModel
        }

        private val _uiState =
            MutableStateFlow(
                ManageHabitUiState(),
            )
        val uiState: StateFlow<ManageHabitUiState> = _uiState.asStateFlow()

        init {
            _uiState.value = _uiState.value.copy(isEditMode = habitId != null)
        }

        fun onNameChange(name: String) {
            _uiState.value =
                _uiState.value.copy(
                    name = name,
                    nameError = null,
                )
        }

        fun onGoalChange(value: String) {
            if (!value.all { it.isDigit() }) return

            _uiState.value =
                _uiState.value.copy(
                    goal = value,
                    goalError = null,
                )
        }

        fun onStepChange(value: String) {
            if (!value.all { it.isDigit() }) return

            _uiState.value =
                _uiState.value.copy(
                    step = value,
                    stepError = null,
                )
        }

        fun onProgressModeChange(value: HabitProgressMode) {
            _uiState.value =
                _uiState.value.copy(
                    progressMode = value,
                )
        }

        fun onUnitChange(value: HabitUnit) {
            _uiState.value =
                _uiState.value.copy(
                    unit = value,
                )
        }

        fun onScheduleTypeChange(value: HabitScheduleType) {
            _uiState.value =
                _uiState.value.copy(
                    scheduleType = value,
                    selectedWeekDaysError = null,
                )
        }

        fun onWeekDayToggle(day: DayOfWeek) {
            val currentDays = _uiState.value.selectedWeekDays

            val newDays =
                if (day in currentDays) {
                    currentDays - day
                } else {
                    currentDays + day
                }

            _uiState.value =
                _uiState.value.copy(
                    selectedWeekDays = newDays,
                    selectedWeekDaysError = null,
                )
        }

        private fun isValid(): Boolean {
            val state = _uiState.value

            val name = state.name.trim()
            val goal = state.goal.trim().toIntOrNull()
            val step = state.step.trim().toIntOrNull()

            val nameError = if (name.isBlank()) "Name is required" else null

            val stepError =
                if (state.progressMode == HabitProgressMode.AddValue && (step == null || step <= 0)) {
                    "Step must be greater than 0"
                } else {
                    null
                }

            val goalError =
                if (goal == null || goal <= 0) {
                    "Goal must be greater than 0"
                } else {
                    null
                }

            val selectedWeekDaysError =
                if (
                    state.scheduleType == HabitScheduleType.SpecificWeekDays &&
                    state.selectedWeekDays.isEmpty()
                ) {
                    "Choose at least one day"
                } else {
                    null
                }

            if (
                nameError != null ||
                goalError != null ||
                stepError != null ||
                selectedWeekDaysError != null
            ) {
                _uiState.value =
                    state.copy(
                        stepError = stepError,
                        nameError = nameError,
                        goalError = goalError,
                        selectedWeekDaysError = selectedWeekDaysError,
                    )

                return false
            }

            return true
        }

        fun onSubmitClick() {
            if (!isValid()) return

            val state = _uiState.value
            val progressMode = requireNotNull(state.progressMode)

            viewModelScope.launch {
                val habit =
                    Habit(
                        id = habitId ?: UUID.randomUUID().toString(),
                        name = state.name,
                        goal = state.goal.toInt(),
                        progressMode = progressMode,
                        unit = state.unit,
                        step = state.step.toIntOrNull() ?: 1,
                        schedule =
                            when (state.scheduleType) {
                                HabitScheduleType.EveryDay -> {
                                    HabitSchedule.EveryDay
                                }

                                HabitScheduleType.SpecificWeekDays -> {
                                    HabitSchedule.SpecificWeekDays(days = state.selectedWeekDays)
                                }
                            },
                        isActive = true,
                    )

                habitRepository.saveHabit(habit)
            }
        }
    }
