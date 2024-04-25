package com.sinasamaki.hapticfeedbackdemo.ui.demos

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.HapticFeedbackConstantsCompat
import com.sinasamaki.hapticfeedbackdemo.ui.components.DemoWindow
import com.sinasamaki.hapticfeedbackdemo.ui.theme.Colors
import com.sinasamaki.hapticfeedbackdemo.ui.theme.HapticFeedbackDemoTheme
import kotlinx.coroutines.flow.drop
import kotlin.math.roundToInt

@Composable
fun TextCursor() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        val density = LocalDensity.current

        Box {
            var textLayoutResult: TextLayoutResult? by remember { mutableStateOf(null) }
            var cursorIndex by rememberSaveable { mutableIntStateOf(1) }
            val view = LocalView.current
            LaunchedEffect(Unit) {
                snapshotFlow { cursorIndex }.drop(1).collect {
                    view.performHapticFeedback(HapticFeedbackConstantsCompat.TEXT_HANDLE_MOVE)
                }
            }
            val rect by remember(textLayoutResult, cursorIndex) {
                derivedStateOf {
                    textLayoutResult?.getBoundingBox(cursorIndex) ?: Rect.Zero
                }
            }
            Box(modifier = Modifier
                .offset {
                    IntOffset(rect.left.toInt(), rect.top.toInt())
                }
                .offset {
                    IntOffset(rect.width.toInt(), 0)
                }
                .offset(x = (-1).dp)
                .width(2.dp)
                .height(with(density) { rect.height.toDp() })
                .padding(bottom = 2.dp)
                .background(Colors.blue, CircleShape)
            )

            var offset by rememberSaveable { mutableIntStateOf(0) }

            var isDragging by remember {
                mutableStateOf(false)
            }

            val text = rememberSaveable { " Hello World" }


            val animatedOffset by animateIntOffsetAsState(
                targetValue = if (isDragging)
                    IntOffset(0, rect.top.toInt()) +
                            IntOffset(0, rect.height.toInt()) +
                            IntOffset(offset, 0)
                else
                    IntOffset(rect.left.toInt(), rect.top.toInt()) +
                            IntOffset(
                                rect.width.toInt() - (with(density) { 8.dp.roundToPx() }),
                                rect.height.toInt()
                            ), label = "",
                animationSpec = if (isDragging) snap() else spring(dampingRatio = Spring.DampingRatioMediumBouncy)
            )

            Box(modifier = Modifier
                .offset {
                    animatedOffset
                }
                .width(16.dp)
                .height(16.dp)
                .background(
                    Colors.blue,
                    CutCornerShape(topStartPercent = 50, topEndPercent = 50)
                )
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragStart = {
                            isDragging = true
                            offset =
                                rect.left.roundToInt() + rect.width.toInt() - (8.dp.roundToPx())
                        },
                        onDragEnd = {
                            isDragging = false
                        },
                        onDrag = { _, dragAmount ->
                            offset += dragAmount.x.roundToInt()
                            textLayoutResult?.let {
                                for (i in 0..text.lastIndex) {
                                    val box = it.getBoundingBox(i)
                                    if (offset.toFloat() in box.left..box.right) {
                                        cursorIndex = i
                                    }
                                }
                            }
                        }
                    )
                }
            )


            Text(
                text = text,
                style = MaterialTheme.typography.headlineLarge,
                letterSpacing = 2.sp,
                onTextLayout = {
                    textLayoutResult = it
                }
            )
        }
    }


}


@Preview(
    showBackground = true,
    backgroundColor = 0xffffffff
)
@Composable
private fun TextCursorPreview() {
    HapticFeedbackDemoTheme {
        DemoWindow {
            TextCursor()
        }
    }
}