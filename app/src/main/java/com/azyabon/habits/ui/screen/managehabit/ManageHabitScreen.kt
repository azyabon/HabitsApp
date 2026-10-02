package com.azyabon.habits.ui.screen.managehabit

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.azyabon.habits.core.designsystem.component.AppButton
import com.azyabon.habits.core.designsystem.component.AppChipGroup
import com.azyabon.habits.core.designsystem.component.AppDropdown
import com.azyabon.habits.core.designsystem.component.AppTextField
import com.azyabon.habits.core.designsystem.component.AppTopBar
import com.azyabon.habits.core.designsystem.component.DatePickerModal
import com.azyabon.habits.domain.model.HabitProgressMode
import com.azyabon.habits.domain.model.HabitUnit
import java.time.DayOfWeek
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageHabitScreen(
    viewModel: ManageHabitViewModel,
    onBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var datePickerTarget by remember { mutableStateOf<DatePickerTarget?>(null) }

    val selectableDates =
        when (datePickerTarget) {
            DatePickerTarget.StartDate -> {
                DatePickerDefaults.AllDates
            }

            DatePickerTarget.EndDate -> {
                object : SelectableDates {
                    override fun isSelectableDate(utcTimeMillis: Long): Boolean = utcTimeMillis >= uiState.startDate.toUtcMillis()
                }
            }

            null -> {
                DatePickerDefaults.AllDates
            }
        }

    ManageHabitContent(
        uiState = uiState,
        onNameChange = { viewModel.onNameChange(it) },
        onDescriptionChange = { viewModel.onDescriptionChange(it) },
        onGoalChange = { viewModel.onGoalChange(it) },
        onStepChange = { viewModel.onStepChange(it) },
        onUnitChange = { viewModel.onUnitChange(it) },
        onProgressModeChange = { viewModel.onProgressModeChange(it) },
        onScheduleTypeChange = { viewModel.onScheduleTypeChange(it) },
        onWeekDayToggle = { viewModel.onWeekDayToggle(it) },
        onStartDateClick = { datePickerTarget = DatePickerTarget.StartDate },
        onEndDateClick = { datePickerTarget = DatePickerTarget.EndDate },
        onSubmitClick = { viewModel.onSubmitClick() },
        onBack = onBack,
    )
    datePickerTarget?.let { target ->
        DatePickerModal(
            initialSelectedDateMillis =
                when (target) {
                    DatePickerTarget.StartDate -> uiState.startDate.toUtcMillis()
                    DatePickerTarget.EndDate -> (uiState.endDate ?: uiState.startDate).toUtcMillis()
                },
            selectableDates = selectableDates,
            onDateSelected = { selectedDateMillis ->
                selectedDateMillis?.toLocalDateString()?.let { selectedDate ->
                    when (target) {
                        DatePickerTarget.StartDate -> {
                            viewModel.onStartDateChange(selectedDate)
                            viewModel.onEndDateChange(null)
                        }

                        DatePickerTarget.EndDate -> {
                            viewModel.onEndDateChange(selectedDate)
                        }
                    }
                }
                datePickerTarget = null
            },
            onDismiss = {
                datePickerTarget = null
            },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageHabitContent(
    uiState: ManageHabitUiState,
    onNameChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onGoalChange: (String) -> Unit,
    onStepChange: (String) -> Unit,
    onUnitChange: (HabitUnit) -> Unit,
    onProgressModeChange: (HabitProgressMode) -> Unit,
    onScheduleTypeChange: (HabitScheduleType) -> Unit,
    onWeekDayToggle: (DayOfWeek) -> Unit,
    onStartDateClick: () -> Unit,
    onEndDateClick: () -> Unit,
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
                    AppTextField(
                        value = uiState.description,
                        onValueChange = onDescriptionChange,
                        label = "Description",
                        minLines = 2,
                        maxLines = 4,
                        placeholder = "Enter habit description",
                        modifier = Modifier.padding(horizontal = 16.dp),
                        keyboardOptions =
                            KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Default,
                            ),
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
                    Row(
                        modifier =
                            Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.clickable(onClick = onStartDateClick),
                        ) {
                            Text(
                                text = "Start date",
                                modifier = Modifier.padding(vertical = 8.dp),
                            )
                            Text(
                                text = uiState.startDate,
                                color = MaterialTheme.colorScheme.surface,
                                modifier =
                                    Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(color = MaterialTheme.colorScheme.primary)
                                        .padding(horizontal = 12.dp, vertical = 4.dp),
                            )
                        }

                        Box(
                            modifier =
                                Modifier
                                    .weight(1f)
                                    .height(2.dp)
                                    .padding(horizontal = 16.dp)
                                    .clip(RoundedCornerShape(2.dp))
                                    .background(color = MaterialTheme.colorScheme.primary),
                        )

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.clickable(onClick = onEndDateClick),
                        ) {
                            Text(
                                text = "End date",
                                modifier = Modifier.padding(vertical = 8.dp),
                            )
                            Text(
                                text = uiState.endDate ?: "No end",
                                color = MaterialTheme.colorScheme.surface,
                                modifier =
                                    Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(color = MaterialTheme.colorScheme.primary)
                                        .padding(horizontal = 12.dp, vertical = 4.dp),
                            )
                        }
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

private fun Long.toLocalDateString(): String =
    Instant
        .ofEpochMilli(this)
        .atZone(ZoneOffset.UTC)
        .toLocalDate()
        .toString()

private fun String.toUtcMillis(): Long =
    LocalDate
        .parse(this)
        .atStartOfDay(ZoneOffset.UTC)
        .toInstant()
        .toEpochMilli()
