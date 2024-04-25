package com.sinasamaki.hapticfeedbackdemo

sealed class Descriptions(val title: String, val description: String) {
    data object TextCursor : Descriptions(
        title = "Text Cursor",
        description = "Move text cursor along the text",
    )

    data object RadioDial : Descriptions(
        title = "Radio Dial",
        description = "Slide the radio dial horizontally",
    )

    data object Keyboard : Descriptions(
        title = "Keyboard",
        description = "Tap the keyboard",
    )

    data object DragAndDrop : Descriptions(
        title = "Drag & Drop",
        description = "Long press and drag the yellow circle around",
    )

    data object Stopwatch : Descriptions(
        title = "Stopwatch",
        description = "Start the stopwatch timer and feel the seconds tick away",
    )
}

val descriptions = listOf(
    Descriptions.TextCursor,
    Descriptions.RadioDial,
    Descriptions.Keyboard,
    Descriptions.DragAndDrop,
    Descriptions.Stopwatch
)