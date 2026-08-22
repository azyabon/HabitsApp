package com.azyabon.habits.core.designsystem.component

import androidx.compose.ui.graphics.vector.ImageVector

data class Option<T>(
    val value: T,
    val label: String,
    val icon: ImageVector? = null,
)
