package com.azyabon.habits.ui.shape

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

class BottomBarShape(
    private val cornerRadius: Dp = 20.dp,
    private val cutoutHalfWidth: Dp = 43.dp,
    private val cutoutDepth: Dp = 50.dp,
    private val transitionWidth: Dp = 20.dp,
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline =
        with(density) {
            val corner = cornerRadius.toPx()
            val halfWidth = cutoutHalfWidth.toPx()
            val depth = cutoutDepth.toPx()
            val transition = transitionWidth.toPx()

            val centerX = size.width / 2f

            val cutoutLeft = centerX - halfWidth
            val cutoutRight = centerX + halfWidth

            val path =
                Path().apply {
                    moveTo(corner, 0f)

                    // Верхняя линия слева
                    lineTo(
                        cutoutLeft - transition,
                        0f,
                    )

                    // Левая половина выемки.
                    //
                    // Первая control point продолжает горизонтальную линию,
                    // вторая формирует круглое дно.
                    cubicTo(
                        cutoutLeft - 4.dp.toPx(),
                        0f,
                        centerX - 38.dp.toPx(),
                        depth,
                        centerX,
                        depth,
                    )

                    // Правая половина
                    cubicTo(
                        centerX + 38.dp.toPx(),
                        depth,
                        cutoutRight + 4.dp.toPx(),
                        0f,
                        cutoutRight + transition,
                        0f,
                    )

                    // Верхняя линия справа
                    lineTo(size.width - corner, 0f)

                    // Правый верхний угол
                    arcTo(
                        rect =
                            Rect(
                                left = size.width - corner * 2f,
                                top = 0f,
                                right = size.width,
                                bottom = corner * 2f,
                            ),
                        startAngleDegrees = -90f,
                        sweepAngleDegrees = 90f,
                        forceMoveTo = false,
                    )

                    // Правая сторона
                    lineTo(
                        size.width,
                        size.height - corner,
                    )

                    // Правый нижний угол
                    arcTo(
                        rect =
                            Rect(
                                left = size.width - corner * 2f,
                                top = size.height - corner * 2f,
                                right = size.width,
                                bottom = size.height,
                            ),
                        startAngleDegrees = 0f,
                        sweepAngleDegrees = 90f,
                        forceMoveTo = false,
                    )

                    // Низ
                    lineTo(corner, size.height)

                    // Левый нижний угол
                    arcTo(
                        rect =
                            Rect(
                                left = 0f,
                                top = size.height - corner * 2f,
                                right = corner * 2f,
                                bottom = size.height,
                            ),
                        startAngleDegrees = 90f,
                        sweepAngleDegrees = 90f,
                        forceMoveTo = false,
                    )

                    // Левая сторона
                    lineTo(0f, corner)

                    // Левый верхний угол
                    arcTo(
                        rect =
                            Rect(
                                left = 0f,
                                top = 0f,
                                right = corner * 2f,
                                bottom = corner * 2f,
                            ),
                        startAngleDegrees = 180f,
                        sweepAngleDegrees = 90f,
                        forceMoveTo = false,
                    )

                    close()
                }

            Outline.Generic(path)
        }
}
