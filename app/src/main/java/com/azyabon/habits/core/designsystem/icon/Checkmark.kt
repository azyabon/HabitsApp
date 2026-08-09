package com.azyabon.habits.core.designsystem.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Checkmark: ImageVector
    get() {
        if (_Checkmark != null) {
            return _Checkmark!!
        }
        _Checkmark =
            ImageVector
                .Builder(
                    name = "Checkmark",
                    defaultWidth = 64.dp,
                    defaultHeight = 64.dp,
                    viewportWidth = 24f,
                    viewportHeight = 24f,
                ).apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 0f,
                        strokeAlpha = 0f,
                    ) {
                        moveTo(0f, 0f)
                        horizontalLineToRelative(24f)
                        verticalLineToRelative(24f)
                        horizontalLineToRelative(-24f)
                        close()
                    }
                    path(fill = SolidColor(Color.Black)) {
                        moveTo(9.86f, 18f)
                        arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.73f, -0.32f)
                        lineToRelative(-4.86f, -5.17f)
                        arcToRelative(1f, 1f, 0f, isMoreThanHalf = true, isPositiveArc = true, 1.46f, -1.37f)
                        lineToRelative(4.12f, 4.39f)
                        lineToRelative(8.41f, -9.2f)
                        arcToRelative(1f, 1f, 0f, isMoreThanHalf = true, isPositiveArc = true, 1.48f, 1.34f)
                        lineToRelative(-9.14f, 10f)
                        arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.73f, 0.33f)
                        close()
                    }
                }.build()

        return _Checkmark!!
    }

@Suppress("ObjectPropertyName")
private var _Checkmark: ImageVector? = null
