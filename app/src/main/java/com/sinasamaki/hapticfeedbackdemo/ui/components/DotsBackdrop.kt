package com.sinasamaki.hapticfeedbackdemo.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke

@Composable
fun DotsBackdrop(
    center: Offset,
) {
    val dotColor = MaterialTheme.colorScheme.onSurface
    Box(modifier = Modifier
        .fillMaxSize()
        .drawWithCache {

            val path = Path()

            val row = 8
            val col = 16
            val width = size.width / col
            val height = size.height / row

            path.moveTo(width / 2, height / 2)
            for (i in 1..row) {
                path.relativeLineTo(
                    (if (i % 2 == 0) -1 else 1) * (size.width - width),
                    0f
                )
                path.relativeLineTo(0f, height)
            }

            val intervals = buildList {
                for (i in 1..row) {
                    for (j in 1..col) {
                        add(0f)
                        if (j != col) add(width)
                    }
                    add(height)
                }
            }.toFloatArray()


            this.onDrawBehind {
                drawPath(
                    path = path,
                    brush = Brush.radialGradient(
                        colors = listOf(
                            dotColor.copy(alpha = .4f),
                            dotColor.copy(alpha = .04f),
                        ),
                        radius = size.height,
                        center = center
                    ),
                    style = Stroke(
                        width = 5f,
                        cap = StrokeCap.Round,
                        pathEffect = PathEffect.dashPathEffect(
                            intervals = intervals,
                            phase = 0f
                        )
                    )
                )
            }
        }
    )
}
