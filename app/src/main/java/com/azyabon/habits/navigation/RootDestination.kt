package com.azyabon.habits.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface RootDestination : NavKey {
    @Serializable
    data object MainTabs : RootDestination

    @Serializable
    data class HabitDetails(
        val habitId: String,
    ) : RootDestination

    @Serializable
    data class ManageHabit(
        val habitId: String? = null,
    ) : RootDestination
}
