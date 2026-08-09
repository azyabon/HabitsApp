package com.azyabon.habits.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.azyabon.habits.core.designsystem.icon.Settings
import com.azyabon.habits.core.designsystem.icon.Stats
import com.azyabon.habits.core.designsystem.icon.Timer
import com.azyabon.habits.core.designsystem.icon.Today
import com.azyabon.habits.navigation.TabDestination

@Composable
fun TabBarItem(
    tab: TabDestination,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier =
            modifier
                .fillMaxHeight()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onClick,
                ),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            modifier = Modifier.size(28.dp),
            imageVector = tab.icon(),
            contentDescription = tab.title,
            tint =
                if (selected) {
                    MaterialTheme.colorScheme.onPrimary
                } else {
                    MaterialTheme.colorScheme.primary
                },
        )

        if (selected) {
            HorizontalDivider(
                color = MaterialTheme.colorScheme.onPrimary,
                thickness = 2.dp,
                modifier =
                    Modifier
                        .width(24.dp)
                        .offset(y = (20).dp),
            )
        }
    }
}

private fun TabDestination.icon(): ImageVector =
    when (this) {
        TabDestination.Today -> Today
        TabDestination.Stats -> Stats
        TabDestination.Focus -> Timer
        TabDestination.Settings -> Settings
    }
