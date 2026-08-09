package com.azyabon.habits.core.designsystem.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Today: ImageVector
    get() {
        if (_Today != null) {
            return _Today!!
        }
        _Today =
            ImageVector
                .Builder(
                    name = "Today",
                    defaultWidth = 64.dp,
                    defaultHeight = 64.dp,
                    viewportWidth = 24f,
                    viewportHeight = 24f,
                ).apply {
                    path(fill = SolidColor(Color.Black)) {
                        moveTo(15f, 17f)
                        curveTo(16.105f, 17f, 17f, 16.105f, 17f, 15f)
                        curveTo(17f, 13.895f, 16.105f, 13f, 15f, 13f)
                        curveTo(13.895f, 13f, 13f, 13.895f, 13f, 15f)
                        curveTo(13f, 16.105f, 13.895f, 17f, 15f, 17f)
                        close()
                    }
                    path(
                        fill = SolidColor(Color.Black),
                        pathFillType = PathFillType.EvenOdd,
                    ) {
                        moveTo(6f, 3f)
                        curveTo(4.343f, 3f, 3f, 4.343f, 3f, 6f)
                        verticalLineTo(18f)
                        curveTo(3f, 19.657f, 4.343f, 21f, 6f, 21f)
                        horizontalLineTo(18f)
                        curveTo(19.657f, 21f, 21f, 19.657f, 21f, 18f)
                        verticalLineTo(6f)
                        curveTo(21f, 4.343f, 19.657f, 3f, 18f, 3f)
                        horizontalLineTo(6f)
                        close()
                        moveTo(5f, 18f)
                        verticalLineTo(7f)
                        horizontalLineTo(19f)
                        verticalLineTo(18f)
                        curveTo(19f, 18.552f, 18.552f, 19f, 18f, 19f)
                        horizontalLineTo(6f)
                        curveTo(5.448f, 19f, 5f, 18.552f, 5f, 18f)
                        close()
                    }
                }.build()

        return _Today!!
    }

@Suppress("ObjectPropertyName")
private var _Today: ImageVector? = null
