package com.azyabon.habits.ui.screen.today

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.azyabon.habits.ui.component.HabitCard
import com.kizitonwose.calendar.compose.WeekCalendar
import com.kizitonwose.calendar.compose.weekcalendar.WeekCalendarState
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import com.kizitonwose.calendar.core.atStartOfMonth
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun TodayScreen(
    viewModel: TodayViewModel = hiltViewModel(),
    onHabitClick: (String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val currentMonth = remember { YearMonth.now() }
    val startDate = remember { currentMonth.minusMonths(100).atStartOfMonth() }
    val endDate = remember { currentMonth.plusMonths(100).atEndOfMonth() }
    val firstDayOfWeek = remember { firstDayOfWeekFromLocale() }

    val state =
        rememberWeekCalendarState(
            startDate = startDate,
            endDate = endDate,
            firstVisibleWeekDate = uiState.currentDate,
            firstDayOfWeek = firstDayOfWeek,
        )

    TodayContent(
        calendarState = state,
        currentDate = uiState.currentDate,
        habits = uiState.habits,
        onDateSelected = viewModel::onDateSelected,
        onHabitClick = onHabitClick,
        onProgressClick = viewModel::onProgressClick,
    )
}

@Composable
fun TodayContent(
    calendarState: WeekCalendarState,
    currentDate: LocalDate,
    habits: List<TodayHabitUi>,
    onDateSelected: (LocalDate) -> Unit,
    onHabitClick: (String) -> Unit,
    onProgressClick: (String) -> Unit,
) {
    LazyColumn(
        modifier =
            Modifier
                .fillMaxSize(),
    ) {
        item {
            Text(
                text = currentDate.toHeaderText(),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            )
            WeekCalendar(
                state = calendarState,
                dayContent = {
                    Day(
                        day = it,
                        isSelected = it.date == currentDate,
                        onClick = { onDateSelected(it.date) },
                    )
                },
                modifier = Modifier.padding(bottom = 16.dp),
            )
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

private fun LocalDate.toHeaderText(): String {
    val today = LocalDate.now()
    val locale = Locale.getDefault()

    return when (this) {
        today -> "Today"
        today.minusDays(1) -> "Yesterday"
        else -> format(DateTimeFormatter.ofPattern("d MMMM", locale))
    }
}
