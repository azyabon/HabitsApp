package com.azyabon.habits.ui.screen.managehabit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.azyabon.habits.core.designsystem.component.AppButton
import com.azyabon.habits.core.designsystem.component.AppChipGroup
import com.azyabon.habits.core.designsystem.component.AppDropdown
import com.azyabon.habits.core.designsystem.component.AppTextField
import com.azyabon.habits.core.designsystem.component.AppTopBar
import com.azyabon.habits.domain.model.HabitCategory
import com.azyabon.habits.domain.model.HabitUnit
import java.time.DayOfWeek

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
        onScheduleTypeChange = { viewModel.onScheduleTypeChange(it) },
        onWeekDayToggle = { viewModel.onWeekDayToggle(it) },
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
    onScheduleTypeChange: (HabitScheduleType) -> Unit,
    onWeekDayToggle: (DayOfWeek) -> Unit,
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
            LazyColumn(
                modifier =
                    Modifier
                        .fillMaxSize(),
                contentPadding = paddingValues,
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                item {
                    AppTextField(
                        value = uiState.name,
                        onValueChange = onNameChange,
                        label = "Name",
                        placeholder = "Enter habit name",
                        error = uiState.nameError,
                        modifier = Modifier.padding(horizontal = 16.dp),
                    )
                }

                item {
                    AppDropdown(
                        label = "Category",
                        placeholder = "Choose category",
                        options = categoryOptions,
                        selectedValue = uiState.category,
                        onOptionSelected = onCategoryChange,
                        error = uiState.categoryError,
                        modifier = Modifier.padding(horizontal = 16.dp),
                    )
                }

                item {
                    AppDropdown(
                        label = "Target type",
                        placeholder = "Choose target type",
                        options = targetTypeOptions,
                        selectedValue = uiState.targetType,
                        onOptionSelected = onTargetTypeChange,
                        error = uiState.targetTypeError,
                        modifier = Modifier.padding(horizontal = 16.dp),
                    )
                }

                if (uiState.targetType == HabitTargetType.Amount) {
                    item {
                        Row(
                            modifier =
                                Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            AppTextField(
                                value = uiState.amount,
                                onValueChange = onAmountChange,
                                label = "Amount",
                                placeholder = "Enter amount",
                                error = uiState.amountError,
                                modifier = Modifier.weight(1f),
                            )

                            AppDropdown(
                                label = "Unit",
                                placeholder = "Choose unit",
                                options = unitOptions,
                                selectedValue = uiState.unit,
                                onOptionSelected = onUnitChange,
                                error = uiState.unitError,
                                modifier = Modifier.weight(1f),
                            )
                        }
                    }
                }

                item {
                    AppDropdown(
                        label = "Frequency",
                        placeholder = "Choose frequency",
                        options = scheduleTypeOptions,
                        selectedValue = uiState.scheduleType,
                        onOptionSelected = onScheduleTypeChange,
                        modifier = Modifier.padding(horizontal = 16.dp),
                    )
                }

                if (uiState.scheduleType == HabitScheduleType.SpecificWeekDays) {
                    item {
                        AppChipGroup(
                            label = "Select Days",
                            options = daysOptions,
                            selectedValues = uiState.selectedWeekDays,
                            onOptionClick = onWeekDayToggle,
                            error = uiState.selectedWeekDaysError,
                        )
                    }
                }

                item {
                    AppButton(
                        text = if (uiState.isEditMode) "Save" else "Create",
                        onClick = onSubmitClick,
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp),
                    )
                }
            }
        },
    )
}
