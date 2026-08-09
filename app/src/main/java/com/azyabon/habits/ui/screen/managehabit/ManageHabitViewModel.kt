package com.azyabon.habits.ui.screen.managehabit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.azyabon.habits.domain.model.HabitCategory
import com.azyabon.habits.domain.model.HabitUnit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ManageHabitViewModel(
    habitId: String?,
) : ViewModel() {
    class Factory(
        private val habitId: String?,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = ManageHabitViewModel(habitId) as T
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
        if (isValid()) {
            // TODO: Save habit
        }
    }
}
