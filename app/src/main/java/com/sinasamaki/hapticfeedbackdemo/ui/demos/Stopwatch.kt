package com.sinasamaki.hapticfeedbackdemo.ui.demos

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.HapticFeedbackConstantsCompat
import com.sinasamaki.hapticfeedbackdemo.R
import com.sinasamaki.hapticfeedbackdemo.ui.components.Button
import com.sinasamaki.hapticfeedbackdemo.ui.components.DemoWindow
import com.sinasamaki.hapticfeedbackdemo.ui.theme.Colors
import com.sinasamaki.hapticfeedbackdemo.ui.theme.HapticFeedbackDemoTheme
import kotlinx.coroutines.delay

@Composable
fun Stopwatch() {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {


        var seconds by rememberSaveable {
            mutableIntStateOf(0)
        }

        val time by remember {
            derivedStateOf {
                "${(seconds / 60).toString().padStart(2, '0')}:${
                    (seconds % 60).toString().padStart(2, '0')
                }"
            }
        }

        var isRunning by rememberSaveable {
            mutableStateOf(false)
        }

        val view = LocalView.current
        LaunchedEffect(Unit) {
            // LOL this is just a demo timer and is not accurate!
            // Do not use this for any mission critical stuff
            // Or anything at all for that matter
            while (true) {
                if (isRunning) {
                    seconds++
                    view.performHapticFeedback(HapticFeedbackConstantsCompat.CLOCK_TICK)
                }
                delay(1000)
            }
        }

        Text(
            text = time,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge
        )

        Button(
            color = Colors.red,
            icon = R.drawable.stop
        ) {
            view.performHapticFeedback(HapticFeedbackConstantsCompat.REJECT)
            seconds = 0
            isRunning = false
        }
        Button(
            color = Colors.green,
            icon = if (isRunning) R.drawable.pause else R.drawable.play
        ) {
            view.performHapticFeedback(HapticFeedbackConstantsCompat.CONFIRM)
            isRunning = !isRunning
        }

    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xffffffff
)
@Composable
private fun StopwatchPreview() {
    HapticFeedbackDemoTheme {
        DemoWindow {
            Stopwatch()
        }
    }
}