package com.azyabon.habits.ui.screen.habit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.azyabon.habits.core.designsystem.component.AppIconButton
import com.azyabon.habits.core.designsystem.component.AppTopBar
import com.azyabon.habits.domain.model.Habit
import com.azyabon.habits.domain.model.HabitProgress

@Composable
fun HabitScreen(
    viewModel: HabitViewModel,
    onBack: () -> Unit,
    onHabitEdit: (habitId: String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val habit = uiState.habit
    val habitProgress = uiState.habitProgress

    if (habit != null && habitProgress != null) {
        HabitContent(
            habit = habit,
            habitProgress = habitProgress,
            onBack = onBack,
            onHabitEdit = onHabitEdit,
        )
    } else {
        Text("Loading...")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitContent(
    habit: Habit,
    habitProgress: HabitProgress,
    onBack: () -> Unit,
    onHabitEdit: (habitId: String) -> Unit,
) {
    Scaffold(
        topBar = {
            AppTopBar(
                title = habit.name,
                onBack = onBack,
                actions = {
                    AppIconButton(
                        onClick = { onHabitEdit(habit.id) },
                        icon = Icons.Default.Edit,
                        contentDescription = "Edit Habit",
                    )
                },
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
                Text("HabitContent: ${habit.name} - Progress: ${habitProgress.currentValue}")
            }
        },
    )
}
