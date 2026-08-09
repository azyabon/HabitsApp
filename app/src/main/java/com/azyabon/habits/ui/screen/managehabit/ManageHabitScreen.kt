package com.azyabon.habits.ui.screen.managehabit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.azyabon.habits.core.designsystem.component.AppButton
import com.azyabon.habits.core.designsystem.component.AppDropdown
import com.azyabon.habits.core.designsystem.component.AppTextField
import com.azyabon.habits.core.designsystem.component.AppTopBar
import com.azyabon.habits.domain.model.HabitCategory
import com.azyabon.habits.domain.model.HabitUnit

@Composable
fun ManageHabitScreen(
    viewModel: ManageHabitViewModel,
    onBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ManageHabitContent(
        uiState = uiState,
        onNameChange = { viewModel.onNameChange(it) },
        onCategoryChange = { viewModel.onCategoryChange(it) },
        onTargetTypeChange = { viewModel.onTargetTypeChange(it) },
        onAmountChange = { viewModel.onAmountChange(it) },
        onUnitChange = { viewModel.onUnitChange(it) },
        onSubmitClick = { viewModel.onSubmitClick() },
        onBack = onBack,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageHabitContent(
    uiState: ManageHabitUiState,
    onNameChange: (String) -> Unit,
    onCategoryChange: (HabitCategory) -> Unit,
    onTargetTypeChange: (HabitTargetType) -> Unit,
    onAmountChange: (String) -> Unit,
    onUnitChange: (HabitUnit) -> Unit,
    onSubmitClick: () -> Unit,
    onBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            AppTopBar(
                title = if (uiState.isEditMode) "Edit Habit" else "Create Habit",
                onBack = onBack,
            )
        },
        content = { paddingValues ->
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(horizontal = 16.dp),
            ) {
                AppTextField(
                    value = uiState.name,
                    onValueChange = { onNameChange(it) },
                    label = "Name",
                    placeholder = "Enter habit name",
                    error = uiState.nameError,
                )

                Spacer(modifier = Modifier.height(8.dp))

                AppDropdown(
                    label = "Category",
                    placeholder = "Choose category",
                    options = categoryOptions,
                    selectedValue = uiState.category,
                    onOptionSelected = { onCategoryChange(it) },
                    error = uiState.categoryError,
                )

                Spacer(modifier = Modifier.height(8.dp))

                AppDropdown(
                    label = "Target type",
                    placeholder = "Choose target type",
                    options = targetTypeOptions,
                    selectedValue = uiState.targetType,
                    onOptionSelected = { onTargetTypeChange(it) },
                    error = uiState.targetTypeError,
                )

                if (uiState.targetType == HabitTargetType.Amount) {
                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier =
                            Modifier.fillMaxWidth(),
                    ) {
                        AppTextField(
                            value = uiState.amount,
                            onValueChange = { onAmountChange(it) },
                            label = "Amount",
                            placeholder = "Enter amount",
                            error = uiState.amountError,
                            modifier = Modifier.weight(1f),
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        AppDropdown(
                            label = "Unit",
                            placeholder = "Choose unit",
                            options = unitOptions,
                            selectedValue = uiState.unit,
                            onOptionSelected = { onUnitChange(it) },
                            error = uiState.unitError,
                            modifier = Modifier.weight(1f),
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                AppButton(
                    text = if (uiState.isEditMode) "Save" else "Create",
                    onClick = onSubmitClick,
                    modifier = Modifier,
                )
            }
        },
    )
}
