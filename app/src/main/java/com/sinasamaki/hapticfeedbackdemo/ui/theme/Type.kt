package com.sinasamaki.hapticfeedbackdemo.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sinasamaki.hapticfeedbackdemo.R

val defaultFontFamily = FontFamily(
    Font(R.font.regular, FontWeight.Normal),
    Font(R.font.semibold, FontWeight.SemiBold),
)

val radioFontFamily = FontFamily(
    Font(R.font.radio, FontWeight.Normal),
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = defaultFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = defaultFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = defaultFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = radioFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
    )
)