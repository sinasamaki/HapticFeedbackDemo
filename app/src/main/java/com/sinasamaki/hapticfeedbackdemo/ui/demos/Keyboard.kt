package com.sinasamaki.hapticfeedbackdemo.ui.demos

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.core.view.HapticFeedbackConstantsCompat
import com.sinasamaki.hapticfeedbackdemo.ui.components.DemoWindow
import com.sinasamaki.hapticfeedbackdemo.ui.theme.Colors
import com.sinasamaki.hapticfeedbackdemo.ui.theme.HapticFeedbackDemoTheme
import com.sinasamaki.hapticfeedbackdemo.ui.theme.getTextColor
import kotlinx.coroutines.flow.drop

@Composable
fun Keyboard() {


    Row(
        modifier = Modifier
            .graphicsLayer {
                rotationX = 5f
            }
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        "QWERTY".split("").filter { it.isNotBlank() }.forEach { letter ->

            val view = LocalView.current
            val interaction = remember { MutableInteractionSource() }

            val isPressed by interaction.collectIsPressedAsState()
            LaunchedEffect(Unit) {
                snapshotFlow { isPressed }
                    .drop(1)
                    .collect {
                        if (it) view.performHapticFeedback(HapticFeedbackConstantsCompat.KEYBOARD_PRESS)
                    }
            }

            Box(
                modifier = Modifier
                    .clickable(
                        indication = null,
                        interactionSource = interaction,
                        onClick = {
                            view.performHapticFeedback(HapticFeedbackConstantsCompat.KEYBOARD_RELEASE)
                        }
                    )
            ) {
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .size(40.dp)
                        .background(Colors.green, RoundedCornerShape(4.dp))
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = .2f)
                                )
                            ),
                            shape = RoundedCornerShape(4.dp)
                        ),
                )
                Box(
                    modifier = Modifier
                        .offset {
                            IntOffset(0, if (isPressed) -1 else -10)
                        }
                        .padding(2.dp)
                        .size(40.dp)
                        .border(
                            width = 2.dp,
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.White.copy(alpha = .2f),
                                    Color.Transparent,
                                    Color.Black.copy(alpha = .05f),
                                )
                            ),
                            shape = RoundedCornerShape(4.dp)
                        )
                        .background(Colors.green, RoundedCornerShape(4.dp)),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = letter,
                        fontWeight = FontWeight.SemiBold,
                        color = Colors.green.getTextColor(),
                    )
                }
            }

        }

    }
}


@Preview(
    showBackground = true,
    backgroundColor = 0xffffffff
)
@Composable
private fun KeyboardPreview() {
    HapticFeedbackDemoTheme {
        DemoWindow {
            Keyboard()
        }
    }
}