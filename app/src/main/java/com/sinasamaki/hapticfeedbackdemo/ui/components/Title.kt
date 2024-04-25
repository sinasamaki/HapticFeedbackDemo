package com.sinasamaki.hapticfeedbackdemo.ui.components

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.view.HapticFeedbackConstantsCompat
import com.sinasamaki.hapticfeedbackdemo.ui.theme.Colors
import com.sinasamaki.hapticfeedbackdemo.utils.ShakeConfig
import com.sinasamaki.hapticfeedbackdemo.utils.rememberShakeController
import com.sinasamaki.hapticfeedbackdemo.utils.shake
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Title(modifier: Modifier = Modifier) {
    val shaker1 = rememberShakeController()
    val shaker2 = rememberShakeController()

    val view = LocalView.current
    val scope = rememberCoroutineScope()
    Box(
        modifier = modifier
            .pointerInput(Unit) {
                detectTapGestures {
                    scope.launch {
                        for (i in 0..7) {
                            view.performHapticFeedback(HapticFeedbackConstantsCompat.LONG_PRESS)
                            delay(25)
                        }
                    }
                    shaker1.shake(
                        ShakeConfig(
                            iterations = 2,
                            translateX = -5f,
                            rotate = -1f,
                        )
                    )
                    shaker2.shake(
                        ShakeConfig(
                            iterations = 2,
                            translateX = 5f,
                            rotate = 1f,
                        )
                    )
                }
            }
            .fillMaxWidth()
            .height(250.dp),
        contentAlignment = Alignment.Center,
    ) {
        Box {
            Text(
                text = "Haptic Feedback Demo",
                modifier = Modifier
                    .shake(shaker1)
                    .padding(horizontal = 64.dp),
                color = Colors.yellow,
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
            )
            Text(
                text = "Haptic Feedback Demo",
                modifier = Modifier
                    .shake(shaker2)
                    .padding(horizontal = 64.dp),
                color = Color.Black,
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
            )
            Text(
                text = "Haptic Feedback Demo",
                modifier = Modifier
                    .padding(horizontal = 64.dp),
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
            )
        }
    }
}