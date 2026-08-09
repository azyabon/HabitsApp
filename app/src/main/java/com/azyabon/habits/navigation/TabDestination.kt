package com.azyabon.habits.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface TabDestination : NavKey {
    val title: String

    @Serializable
    data object Today : TabDestination {
        override val title: String = "Today"
    }

    @Serializable
    data object Focus : TabDestination {
        override val title: String = "Focus"
    }

    @Serializable
    data object Stats : TabDestination {
        override val title: String = "Statistics"
    }

    @Serializable
    data object Settings : TabDestination {
        override val title: String = "Settings"
    }
}
