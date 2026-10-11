package com.azyabon.habits.ui.screen.today

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLocale
import androidx.compose.ui.unit.dp
import com.kizitonwose.calendar.core.WeekDay
import java.time.format.TextStyle

@Composable
fun Day(
    day: WeekDay,
    isSelected: Boolean = false,
    onClick: () -> Unit,
) {
    val shape = RoundedCornerShape(12.dp)
    val selectedColor = MaterialTheme.colorScheme.primary

    Column(
        modifier =
            Modifier
                .aspectRatio(1f)
                .clip(shape)
                .clickable { onClick() }
                .border(
                    width = 1.dp,
                    color = if (isSelected) selectedColor else Color.Transparent,
                    shape = shape,
                ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text =
                day.date.dayOfWeek.getDisplayName(
                    TextStyle.SHORT,
                    LocalLocale.current.platformLocale,
                ),
        )
        Text(
            text = day.date.dayOfMonth.toString(),
        )
    }
}
