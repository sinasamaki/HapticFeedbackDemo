package com.sinasamaki.hapticfeedbackdemo.ui.theme

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils

object Colors {

    val purple: Color
        @Composable @ReadOnlyComposable
        get() = theme(
            dark = Color(0xFFCC79FF),
            light = Color(0xFFB978E0),
        )

    val blue: Color
        @Composable @ReadOnlyComposable
        get() = theme(
            dark = Color(0xFF4C7FE6),
            light = Color(0xFF4FAEB6),
        )

    val green: Color
        @Composable @ReadOnlyComposable
        get() = theme(
            dark = Color(0xFFACF545),
            light = Color(0xFFB6E97B),
        )

    val yellow: Color
        @Composable @ReadOnlyComposable
        get() = theme(
            dark = Color(0xFFFFDF3C),
            light = Color(0xFFC09E43),
        )

    val red: Color
        @Composable @ReadOnlyComposable
        get() = theme(
            dark = Color(0xFFEE5D5D),
            light = Color(0xFFAF3E3E),
        )

    @Composable
    @ReadOnlyComposable
    private fun theme(dark: Color, light: Color): Color {
        return when (isSystemInDarkTheme()) {
            true -> dark
            else -> light
        }
    }
}

fun Color.getTextColor() = if (ColorUtils.calculateContrast(
        android.graphics.Color.WHITE,
        this.toIntColor()
    ) > 4
) Color.White else Color.Black

private fun Color.toIntColor() = android.graphics.Color.argb(
    (alpha * 255).toInt(),
    (red * 255).toInt(),
    (green * 255).toInt(),
    (blue * 255).toInt(),
)

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xff000000,
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xffffffff,
)
@Composable
private fun ColorPalettePreview() {

    Column {
        listOf(
            Colors.purple,
            Colors.blue,
            Colors.green,
            Colors.yellow,
            Colors.red,
        ).forEach { color ->
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    color, Color.Transparent
                                )
                            ),
                            shape = RoundedCornerShape(4.dp)
                        )
                )

                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(
                            color = color, shape = CircleShape
                        )
                )

                Text(
                    text = "Hello",
                    color = color,
                )

                Icon(
                    imageVector = Icons.Rounded.FavoriteBorder,
                    contentDescription = null,
                    tint = color
                )

                Box(
                    modifier = Modifier

                        .background(color = color, shape = RoundedCornerShape(4.dp))
                        .padding(16.dp)
                ) {
                    Text(text = "Goodbye", color = color.getTextColor())
                }

                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .border(
                            width = 2.dp,
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    color,
                                )
                            ),
                            shape = RoundedCornerShape(4.dp)
                        )
                )
            }

        }
    }
}