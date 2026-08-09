package com.azyabon.habits.core.designsystem.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Stats: ImageVector
    get() {
        if (_Stats != null) {
            return _Stats!!
        }
        _Stats =
            ImageVector
                .Builder(
                    name = "Stats",
                    defaultWidth = 64.dp,
                    defaultHeight = 64.dp,
                    viewportWidth = 24f,
                    viewportHeight = 24f,
                ).apply {
                    path(fill = SolidColor(Color.Black)) {
                        moveTo(19f, 3f)
                        lineTo(5f, 3f)
                        arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, -2f, 2f)
                        verticalLineToRelative(14f)
                        arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, 2f, 2f)
                        horizontalLineToRelative(14f)
                        arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, 2f, -2f)
                        lineTo(21f, 5f)
                        arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, -2f, -2f)
                        close()
                        moveTo(19f, 19f)
                        lineTo(5f, 19f)
                        lineTo(5f, 5f)
                        horizontalLineToRelative(14f)
                        verticalLineToRelative(14f)
                        close()
                        moveTo(9f, 17f)
                        lineTo(7f, 17f)
                        lineTo(7f, 7f)
                        horizontalLineToRelative(2f)
                        verticalLineToRelative(10f)
                        close()
                        moveTo(13f, 17f)
                        horizontalLineToRelative(-2f)
                        verticalLineToRelative(-7f)
                        horizontalLineToRelative(2f)
                        verticalLineToRelative(7f)
                        close()
                        moveTo(17f, 17f)
                        horizontalLineToRelative(-2f)
                        verticalLineToRelative(-5f)
                        horizontalLineToRelative(2f)
                        verticalLineToRelative(5f)
                        close()
                    }
                }.build()

        return _Stats!!
    }

@Suppress("ObjectPropertyName")
private var _Stats: ImageVector? = null
