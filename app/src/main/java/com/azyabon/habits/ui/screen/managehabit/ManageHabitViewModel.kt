package com.azyabon.habits.ui.screen.managehabit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azyabon.habits.data.repository.HabitRepository
import com.azyabon.habits.domain.model.Habit
import com.azyabon.habits.domain.model.HabitCategory
import com.azyabon.habits.domain.model.HabitTarget
import com.azyabon.habits.domain.model.HabitUnit
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
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

        private val _uiState = MutableStateFlow(ManageHabitUiState())
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

        fun onCategoryChange(category: HabitCategory) {
            _uiState.value =
                _uiState.value.copy(
                    category = category,
                    categoryError = null,
                )
        }

        fun onTargetTypeChange(value: HabitTargetType) {
            _uiState.value =
                _uiState.value.copy(
                    targetType = value,
                    targetTypeError = null,
                    amountError = null,
                    unitError = null,
                )
        }

        fun onAmountChange(value: String) {
            _uiState.value =
                _uiState.value.copy(
                    amount = value,
                    amountError = null,
                )
        }

        fun onUnitChange(value: HabitUnit) {
            _uiState.value =
                _uiState.value.copy(
                    unit = value,
                    unitError = null,
                )
        }

        private fun isValid(): Boolean {
            val state = _uiState.value

            val name = state.name.trim()
            val category = state.category
            val targetType = state.targetType
            val unit = state.unit
            val amount = state.amount.trim()

            val nameError = if (name.isBlank()) "Name is required" else null
            val categoryError = if (category == null) "Category is required" else null
            val targetTypeError = if (targetType == null) "Target type is required" else null

            val amountValue =
                if (targetType == HabitTargetType.Amount) {
                    amount.toIntOrNull()
                } else {
                    null
                }

            val amountError =
                if (targetType == HabitTargetType.Amount && (amountValue == null || amountValue <= 0)) {
                    "Amount must be greater than 0"
                } else {
                    null
                }

            val unitError =
                if (targetType == HabitTargetType.Amount && unit == null) {
                    "Unit is required"
                } else {
                    null
                }

            if (
                nameError != null ||
                categoryError != null ||
                targetTypeError != null ||
                amountError != null ||
                unitError != null
            ) {
                _uiState.value =
                    state.copy(
                        nameError = nameError,
                        categoryError = categoryError,
                        targetTypeError = targetTypeError,
                        amountError = amountError,
                        unitError = unitError,
                    )

                return false
            }

            return true
        }

        fun onSubmitClick() {
            if (!isValid()) return

            val state = _uiState.value
            val category = requireNotNull(state.category)
            val targetType = requireNotNull(state.targetType)

            viewModelScope.launch {
                val habit =
                    Habit(
                        id = habitId ?: UUID.randomUUID().toString(),
                        name = state.name,
                        category = category,
                        target =
                            when (targetType) {
                                HabitTargetType.CheckOff -> {
                                    HabitTarget.CheckOff
                                }

                                HabitTargetType.Amount -> {
                                    HabitTarget.Amount(
                                        value = state.amount.toInt(),
                                        unit = requireNotNull(state.unit),
                                    )
                                }
                            },
                        isActive = true,
                    )

                habitRepository.saveHabit(habit)
            }
        }
    }
