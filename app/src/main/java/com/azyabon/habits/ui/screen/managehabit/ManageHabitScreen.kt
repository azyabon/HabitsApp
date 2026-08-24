package com.azyabon.habits.ui.screen.managehabit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.azyabon.habits.core.designsystem.component.AppButton
import com.azyabon.habits.core.designsystem.component.AppChipGroup
import com.azyabon.habits.core.designsystem.component.AppDropdown
import com.azyabon.habits.core.designsystem.component.AppTextField
import com.azyabon.habits.core.designsystem.component.AppTopBar
import com.azyabon.habits.domain.model.HabitProgressMode
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
        onGoalChange = { viewModel.onGoalChange(it) },
        onStepChange = { viewModel.onStepChange(it) },
        onUnitChange = { viewModel.onUnitChange(it) },
        onProgressModeChange = { viewModel.onProgressModeChange(it) },
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
    onGoalChange: (String) -> Unit,
    onStepChange: (String) -> Unit,
    onUnitChange: (HabitUnit) -> Unit,
    onProgressModeChange: (HabitProgressMode) -> Unit,
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
                    Row(
                        modifier =
                            Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        AppTextField(
                            value = uiState.goal,
                            onValueChange = onGoalChange,
                            label = "Goal",
                            placeholder = "0",
                            keyboardOptions =
                                KeyboardOptions(
                                    keyboardType = KeyboardType.NumberPassword,
                                ),
                            error = uiState.goalError,
                            modifier = Modifier.weight(1f),
                        )

                        AppDropdown(
                            label = "Unit",
                            placeholder = "Choose unit",
                            options = unitOptions,
                            selectedValue = uiState.unit,
                            onOptionSelected = onUnitChange,
                            modifier = Modifier.weight(1f),
                        )
                    }
                }

                item {
                    Row(
                        modifier =
                            Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        AppDropdown(
                            label = "Progress Mode",
                            placeholder = "Choose progress mode",
                            options = progressModeOptions,
                            selectedValue = uiState.progressMode,
                            onOptionSelected = onProgressModeChange,
                            modifier = Modifier.weight(1f),
                        )

                        if (uiState.progressMode === HabitProgressMode.AddValue) {
                            AppTextField(
                                value = uiState.step,
                                onValueChange = onStepChange,
                                label = "Step",
                                placeholder = "0",
                                keyboardOptions =
                                    KeyboardOptions(
                                        keyboardType = KeyboardType.NumberPassword,
                                    ),
                                error = uiState.stepError,
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
