package com.azyabon.habits.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme =
    darkColorScheme(
        primary = LightSagePrimary,
        onPrimary = Color(0xFF213020),
        primaryContainer = DarkSageContainer,
        onPrimaryContainer = Color(0xFFD4E8CE),
        secondary = Color(0xFFBFCBB9),
        onSecondary = Color(0xFF293325),
        secondaryContainer = Color(0xFF3F493B),
        onSecondaryContainer = Color(0xFFDBE7D4),
        tertiary = LightGold,
        onTertiary = Color(0xFF3D2F00),
        tertiaryContainer = DarkGoldContainer,
        onTertiaryContainer = Color(0xFFF7DEA0),
        background = DarkBackground,
        onBackground = LightText,
        surface = DarkSurface,
        onSurface = LightText,
        surfaceContainer = DarkSurfaceVariant,
        surfaceVariant = DarkSurfaceVariant,
        onSurfaceVariant = MutedLightText,
        outline = DarkOutline,
        error = DarkError,
        onError = Color(0xFF690005),
        errorContainer = DarkErrorContainer,
        onErrorContainer = Color(0xFFFFDAD6),
    )

private val LightColorScheme =
    lightColorScheme(
        primary = Sage,
        onPrimary = DarkGreen,
        primaryContainer = LightSage,
        onPrimaryContainer = DarkGreen,
        secondary = LightSage,
        onSecondary = DarkGreen,
        secondaryContainer = LightSage,
        onSecondaryContainer = DarkGreen,
        tertiary = Gold,
        onTertiary = DarkGold,
        tertiaryContainer = Color(0xFFF0D9A5),
        onTertiaryContainer = DarkGold,
        background = WarmBackground,
        onBackground = DarkText,
        surface = WarmBackground,
        onSurface = DarkText,
        surfaceContainer = LightSage,
        surfaceVariant = LightSage,
        onSurfaceVariant = MutedText,
        outline = Outline,
        error = ErrorRed,
        onError = Color.White,
        errorContainer = Color(0xFFFFDAD6),
        onErrorContainer = Color(0xFF410002),
    )

@Composable
fun HabitsAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme =
            if (darkTheme) {
                DarkColorScheme
            } else {
                LightColorScheme
            },
        typography = Typography,
        content = content,
    )
}
