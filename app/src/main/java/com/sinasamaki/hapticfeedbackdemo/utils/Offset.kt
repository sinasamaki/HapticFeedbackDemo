package com.sinasamaki.hapticfeedbackdemo.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntOffset
import kotlin.math.roundToInt

fun Offset.toIntOffset() = IntOffset(x.roundToInt(), y.roundToInt())
