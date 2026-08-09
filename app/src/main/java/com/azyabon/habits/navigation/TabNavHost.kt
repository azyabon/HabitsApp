package com.azyabon.habits.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.azyabon.habits.core.designsystem.component.AppTopBar
import com.azyabon.habits.core.designsystem.icon.Add
import com.azyabon.habits.ui.component.TabBarItem
import com.azyabon.habits.ui.screen.stats.StatsScreen
import com.azyabon.habits.ui.screen.today.TodayScreen
import com.azyabon.habits.ui.shape.BottomBarShape

private val leftTabs =
    listOf(
        TabDestination.Today,
        TabDestination.Stats,
    )

private val rightTabs =
    listOf(
        TabDestination.Focus,
        TabDestination.Settings,
    )

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabNavHost(
    onHabitClick: (
        String,
    ) -> Unit,
    onHabitCreate: () -> Unit,
) {
    val todayStack = rememberNavBackStack(TabDestination.Today)
    val focusStack = rememberNavBackStack(TabDestination.Focus)
    val statsStack = rememberNavBackStack(TabDestination.Stats)
    val settingsStack = rememberNavBackStack(TabDestination.Settings)

    var selectedTab by remember { mutableStateOf<TabDestination>(TabDestination.Today) }

    val activeStack =
        when (selectedTab) {
            TabDestination.Today -> todayStack
            TabDestination.Focus -> focusStack
            TabDestination.Stats -> statsStack
            TabDestination.Settings -> settingsStack
        }

    Scaffold(
        topBar =
            {
                if (selectedTab != TabDestination.Today) {
                    AppTopBar(
                        title = selectedTab.title,
                    )
                }
            },
        bottomBar = {
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .windowInsetsPadding(WindowInsets.navigationBars),
            ) {
                NavigationBar(
                    windowInsets =
                        WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .height(70.dp)
                            .padding(bottom = 8.dp)
                            .clip(
                                BottomBarShape(
                                    cornerRadius = 20.dp,
                                    cutoutHalfWidth = 43.dp,
                                    cutoutDepth = 50.dp,
                                    transitionWidth = 20.dp,
                                ),
                            ),
                ) {
                    leftTabs.forEach { tab ->
                        TabBarItem(
                            tab = tab,
                            selected = selectedTab == tab,
                            onClick = {
                                if (selectedTab == tab) {
                                    while (activeStack.size > 1) activeStack.removeLastOrNull()
                                } else {
                                    selectedTab = tab
                                }
                            },
                            modifier = Modifier.weight(1f),
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    rightTabs.forEach { tab ->
                        TabBarItem(
                            tab = tab,
                            selected = selectedTab == tab,
                            onClick = {
                                if (selectedTab == tab) {
                                    while (activeStack.size > 1) activeStack.removeLastOrNull()
                                } else {
                                    selectedTab = tab
                                }
                            },
                            modifier = Modifier.weight(1f),
                        )
                    }
                }

                FilledIconButton(
                    onClick = onHabitCreate,
                    modifier =
                        Modifier
                            .align(Alignment.TopCenter)
                            .size(64.dp)
                            .offset(y = (-22).dp)
                            .shadow(
                                elevation = 2.dp,
                                shape = CircleShape,
                                clip = false,
                            ),
                    colors =
                        IconButtonDefaults.filledIconButtonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                        ),
                ) {
                    Icon(
                        modifier = Modifier.size(28.dp),
                        imageVector = Add,
                        contentDescription = "Add",
                        tint = MaterialTheme.colorScheme.onPrimary,
                    )
                }
            }
        },
    ) { paddingValues ->
        NavDisplay(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
            backStack = activeStack,
            onBack = {
                if (activeStack.size > 1) {
                    activeStack.removeLastOrNull()
                }
            },
            entryProvider =
                entryProvider {
                    entry<TabDestination.Today> {
                        TodayScreen(onHabitClick = onHabitClick)
                    }

                    entry<TabDestination.Stats> {
                        StatsScreen()
                    }

                    entry<TabDestination.Focus> {
                        Text("Focus")
                    }

                    entry<TabDestination.Settings> {
                        Text("Settings")
                    }
                },
        )
    }
}
