package com.azyabon.habits.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.azyabon.habits.ui.screen.habit.HabitScreen
import com.azyabon.habits.ui.screen.habit.HabitViewModel
import com.azyabon.habits.ui.screen.managehabit.ManageHabitScreen
import com.azyabon.habits.ui.screen.managehabit.ManageHabitViewModel

@Composable
fun AppNavHost() {
    val backStack = rememberNavBackStack(RootDestination.MainTabs)

    NavDisplay(
        backStack = backStack,
        onBack = {
            if (backStack.size > 1) {
                backStack.removeLastOrNull()
            }
        },
        entryDecorators =
            listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator(),
            ),
        entryProvider =
            entryProvider {
                entry<RootDestination.MainTabs> {
                    TabNavHost(
                        onHabitClick = { habitId ->
                            backStack.add(RootDestination.HabitDetails(habitId))
                        },
                        onHabitCreate = {
                            backStack.add(RootDestination.ManageHabit())
                        },
                    )
                }
                entry<RootDestination.HabitDetails> { destination ->
                    val viewModel: HabitViewModel =
                        viewModel(
                            factory = HabitViewModel.Factory(destination.habitId),
                        )

                    HabitScreen(
                        viewModel = viewModel,
                        onBack = {
                            backStack.removeLastOrNull()
                        },
                        onHabitEdit = { habitId ->
                            backStack.add(RootDestination.ManageHabit(habitId))
                        },
                    )
                }
                entry<RootDestination.ManageHabit> { destination ->
                    val viewModel: ManageHabitViewModel =
                        viewModel(
                            factory = ManageHabitViewModel.Factory(destination.habitId),
                        )

                    ManageHabitScreen(
                        viewModel = viewModel,
                        onBack = {
                            backStack.removeLastOrNull()
                        },
                    )
                }
            },
    )
}
