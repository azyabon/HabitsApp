package com.azyabon.habits.ui.screen.today

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.azyabon.habits.core.designsystem.icon.Streak
import com.azyabon.habits.ui.component.HabitCard

@Composable
fun TodayScreen(
    viewModel: TodayViewModel = hiltViewModel(),
    onHabitClick: (String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    TodayContent(
        habits = uiState.habits,
        onHabitClick = onHabitClick,
        onProgressClick = viewModel::onProgressClick,
    )
}

@Composable
fun TodayContent(
    habits: List<TodayHabitUi>,
    onHabitClick: (String) -> Unit,
    onProgressClick: (String) -> Unit,
) {
    LazyColumn(
        modifier =
            Modifier
                .fillMaxSize(),
    ) {
        item {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            ) {
                Text(
                    text = "Hi, Andrey",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        imageVector = Streak,
                        contentDescription = "Streak",
                        modifier = Modifier.size(28.dp),
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "14",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }

        items(
            items = habits,
            key = { habit -> habit.id },
        ) { habit ->
            HabitCard(
                onClick = { onHabitClick(habit.id) },
                onProgressClick = { onProgressClick(habit.id) },
                name = habit.name,
                isDone = habit.isDone,
                progressText = habit.progressText,
            )
            Spacer(modifier = Modifier.height(6.dp))
        }
    }
}
