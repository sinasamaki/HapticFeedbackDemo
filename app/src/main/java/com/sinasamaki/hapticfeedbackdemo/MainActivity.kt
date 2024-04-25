package com.sinasamaki.hapticfeedbackdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sinasamaki.hapticfeedbackdemo.ui.demos.DragAndDrop
import com.sinasamaki.hapticfeedbackdemo.ui.demos.TextCursor
import com.sinasamaki.hapticfeedbackdemo.ui.demos.Keyboard
import com.sinasamaki.hapticfeedbackdemo.ui.demos.RadioDial
import com.sinasamaki.hapticfeedbackdemo.ui.demos.Stopwatch
import com.sinasamaki.hapticfeedbackdemo.ui.components.DemoWindow
import com.sinasamaki.hapticfeedbackdemo.ui.components.Footer
import com.sinasamaki.hapticfeedbackdemo.ui.components.Title
import com.sinasamaki.hapticfeedbackdemo.ui.theme.HapticFeedbackDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HapticFeedbackDemoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {

    LazyColumn {

        item { Title() }

        items(descriptions) { description ->
            DemoWindow(
                title = description.title,
                description = description.description,
            ) {
                when (description) {
                    is Descriptions.TextCursor -> TextCursor()
                    is Descriptions.RadioDial -> RadioDial()
                    is Descriptions.Keyboard -> Keyboard()
                    is Descriptions.DragAndDrop -> DragAndDrop()
                    is Descriptions.Stopwatch -> Stopwatch()
                }
            }
        }

        item { Footer() }
    }


}
