package com.sinasamaki.hapticfeedbackdemo.ui.demos

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.core.view.HapticFeedbackConstantsCompat
import com.sinasamaki.hapticfeedbackdemo.ui.components.DemoWindow
import com.sinasamaki.hapticfeedbackdemo.ui.components.DotsBackdrop
import com.sinasamaki.hapticfeedbackdemo.ui.theme.Colors
import com.sinasamaki.hapticfeedbackdemo.ui.theme.HapticFeedbackDemoTheme
import com.sinasamaki.hapticfeedbackdemo.utils.toIntOffset
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.map


@Composable
fun DragAndDrop() {

    var bounds by remember {
        mutableStateOf(IntSize.Zero)
    }

    Box(
        modifier = Modifier
            .padding(4.dp)
            .onSizeChanged {
                bounds = it
            }
            .fillMaxSize(),
    ) {
        var offset by remember {
            mutableStateOf(
                Offset.Zero
            )
        }

        val view = LocalView.current
        val density = LocalDensity.current
        val circumferencePx = remember {
            with(density) { 48.dp.roundToPx() }
        }
        var isDragging by remember { mutableStateOf(false) }

        LaunchedEffect(isDragging) {
            if (!isDragging) {
                offset = Offset(
                    offset.x.coerceIn(
                        0f..(bounds.width.toFloat() - circumferencePx).coerceAtLeast(
                            0f
                        )
                    ),
                    offset.y.coerceIn(
                        0f..(bounds.height.toFloat() - circumferencePx).coerceAtLeast(
                            0f
                        )
                    ),
                )
            }
        }

        val animatedOffset by animateOffsetAsState(
            targetValue = offset,
            label = "",
            animationSpec = if (isDragging) snap()
            else spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessMediumLow,
            )
        )


        DotsBackdrop(center = animatedOffset)

        LaunchedEffect(Unit) {
            val step = 50
            snapshotFlow { offset }
                .map { offset.toIntOffset() }
                .map { IntOffset(it.x / step, it.y / step) }
                .distinctUntilChanged()
                .drop(1)
                .collect {
                    view.performHapticFeedback(HapticFeedbackConstantsCompat.CLOCK_TICK)
                }
        }

        Box(
            modifier = Modifier
                .offset { animatedOffset.toIntOffset() }
                .pointerInput(Unit) {
                    detectDragGesturesAfterLongPress(
                        onDragStart = {
                            isDragging = true
                            view.performHapticFeedback(HapticFeedbackConstantsCompat.LONG_PRESS)
                        },
                        onDragEnd = {
                            isDragging = false
                            view.performHapticFeedback(HapticFeedbackConstantsCompat.LONG_PRESS)
                        },
                        onDrag = { _, dragOffset ->
                            offset += dragOffset
                        }
                    )
                }
                .size(48.dp)
                .scale(if (isDragging) 1.1f else 1f)
                .background(Colors.yellow, CircleShape)
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xffffffff
)
@Composable
private fun DragAndDropPreview() {
    HapticFeedbackDemoTheme {
        DemoWindow {
            DragAndDrop()
        }
    }
}