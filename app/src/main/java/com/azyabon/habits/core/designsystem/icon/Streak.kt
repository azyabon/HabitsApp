package com.azyabon.habits.core.designsystem.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Streak: ImageVector
    get() {
        if (_Streak != null) {
            return _Streak!!
        }
        _Streak =
            ImageVector
                .Builder(
                    name = "Streak",
                    defaultWidth = 64.dp,
                    defaultHeight = 64.dp,
                    viewportWidth = 512f,
                    viewportHeight = 512f,
                ).apply {
                    path(fill = SolidColor(Color(0xFFFF8F1F))) {
                        moveTo(266.91f, 500.44f)
                        curveToRelative(-168.74f, 0f, -213.82f, -175.9f, -193.44f, -291.15f)
                        curveToRelative(0.89f, -5.02f, 7.46f, -6.46f, 10.33f, -2.25f)
                        curveToRelative(8.87f, 13.04f, 16.77f, 31.88f, 29.85f, 30.24f)
                        curveToRelative(19.66f, -2.46f, 33.28f, -175.95f, 149.81f, -224.76f)
                        curveToRelative(3.7f, -1.55f, 7.57f, 1.39f, 7.16f, 5.38f)
                        curveToRelative(-5.76f, 56.53f, 28.18f, 137.47f, 88.32f, 137.47f)
                        curveToRelative(34.47f, 0f, 58.06f, -27.51f, 69.84f, -55.14f)
                        curveToRelative(3.58f, -8.39f, 15.84f, -7.34f, 17.9f, 1.56f)
                        curveToRelative(21.03f, 91.08f, 77.25f, 398.66f, -179.76f, 398.66f)
                        close()
                    }
                    path(fill = SolidColor(Color(0xFFFFB636))) {
                        moveTo(207.76f, 330.83f)
                        curveToRelative(3.97f, -3.33f, 9.99f, -1.05f, 10.89f, 4.06f)
                        curveToRelative(2.11f, 11.94f, 9.04f, 32.47f, 31.78f, 32.47f)
                        curveToRelative(27.35f, 0f, 45.91f, -75.26f, 50.78f, -97.4f)
                        curveToRelative(0.8f, -3.64f, 4.35f, -6.11f, 8f, -5.37f)
                        curveToRelative(68.36f, 13.9f, 101.59f, 235.86f, -48.7f, 235.86f)
                        curveToRelative(-109.41f, 0f, -84.63f, -142.84f, -52.75f, -169.61f)
                        close()
                        moveTo(394.54f, 90.45f)
                        curveToRelative(2.41f, -18.84f, -31.99f, 32.69f, -31.99f, 32.69f)
                        reflectiveCurveToRelative(26.22f, 12.39f, 31.99f, -32.69f)
                        close()
                        moveTo(47.96f, 371.46f)
                        curveToRelative(0.73f, -8.02f, -9.59f, -29.5f, -11.42f, -20.99f)
                        curveToRelative(-4.37f, 20.34f, 10.7f, 29.02f, 11.42f, 20.99f)
                        close()
                    }
                    path(fill = SolidColor(Color(0xFFFFD469))) {
                        moveTo(323.18f, 348.6f)
                        curveToRelative(-2.56f, -10.69f, -11.76f, 14.14f, -10.6f, 24.25f)
                        curveToRelative(1.15f, 10.11f, 16.73f, 1.32f, 10.6f, -24.25f)
                        close()
                    }
                }.build()

        return _Streak!!
    }

@Suppress("ObjectPropertyName")
private var _Streak: ImageVector? = null
