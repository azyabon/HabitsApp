package com.azyabon.habits.core.designsystem.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Timer: ImageVector
    get() {
        if (_Timer != null) {
            return _Timer!!
        }
        _Timer =
            ImageVector
                .Builder(
                    name = "Timer",
                    defaultWidth = 64.dp,
                    defaultHeight = 64.dp,
                    viewportWidth = 24f,
                    viewportHeight = 24f,
                ).apply {
                    path(
                        stroke = SolidColor(Color.Black),
                        strokeLineWidth = 2f,
                    ) {
                        moveTo(21f, 12f)
                        curveTo(21f, 16.971f, 16.971f, 21f, 12f, 21f)
                        curveTo(7.029f, 21f, 3f, 16.971f, 3f, 12f)
                        curveTo(3f, 7.029f, 7.029f, 3f, 12f, 3f)
                        curveTo(16.971f, 3f, 21f, 7.029f, 21f, 12f)
                        close()
                    }
                    path(
                        stroke = SolidColor(Color.Black),
                        strokeLineWidth = 2f,
                        strokeLineCap = StrokeCap.Round,
                        strokeLineJoin = StrokeJoin.Round,
                    ) {
                        moveTo(12f, 7f)
                        lineTo(12f, 12f)
                    }
                    path(
                        stroke = SolidColor(Color.Black),
                        strokeLineWidth = 2f,
                        strokeLineCap = StrokeCap.Round,
                        strokeLineJoin = StrokeJoin.Round,
                    ) {
                        moveTo(21f, 4f)
                        lineTo(20f, 3f)
                    }
                }.build()

        return _Timer!!
    }

@Suppress("ObjectPropertyName")
private var _Timer: ImageVector? = null
