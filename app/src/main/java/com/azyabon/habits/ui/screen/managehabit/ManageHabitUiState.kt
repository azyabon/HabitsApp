package com.azyabon.habits.ui.screen.managehabit

import com.azyabon.habits.domain.model.HabitCategory
import com.azyabon.habits.domain.model.HabitUnit

data class ManageHabitUiState(
    val name: String = "",
    val category: HabitCategory? = null,
    val targetType: HabitTargetType? = null,
    val amount: String = "",
    val unit: HabitUnit? = null,
    val isEditMode: Boolean = false,
    val nameError: String? = null,
    val amountError: String? = null,
    val categoryError: String? = null,
    val targetTypeError: String? = null,
    val unitError: String? = null,
)
