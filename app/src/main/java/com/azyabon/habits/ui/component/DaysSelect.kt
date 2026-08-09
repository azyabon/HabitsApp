package com.azyabon.habits.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.DayOfWeek

@Composable
fun DaysSelect(
    selectedDays: Set<DayOfWeek>,
    onDayClick: (DayOfWeek) -> Unit,
    error: String? = null,
) {
    Column {
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp),
        ) {
            DayOfWeek.entries.forEach { day ->
                FilterChip(
                    selected = day in selectedDays,
                    onClick = { onDayClick(day) },
                    label = {
                        Text(text = day.shortLabel(), fontSize = 16.sp)
                    },
                )
            }
        }

        error?.let {
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                fontSize = 14.sp,
            )
        }
    }
}

private fun DayOfWeek.shortLabel(): String =
    when (this) {
        DayOfWeek.MONDAY -> "Mon"
        DayOfWeek.TUESDAY -> "Tue"
        DayOfWeek.WEDNESDAY -> "Wed"
        DayOfWeek.THURSDAY -> "Thu"
        DayOfWeek.FRIDAY -> "Fri"
        DayOfWeek.SATURDAY -> "Sat"
        DayOfWeek.SUNDAY -> "Sun"
    }
