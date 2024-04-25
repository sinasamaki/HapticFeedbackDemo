package com.sinasamaki.hapticfeedbackdemo.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.core.view.HapticFeedbackConstantsCompat
import com.sinasamaki.hapticfeedbackdemo.ui.theme.getTextColor
import kotlinx.coroutines.flow.drop

@Composable
fun Button(
    color: Color,
    @DrawableRes icon: Int,
    onClick: () -> Unit,
) {
    val interaction = remember { MutableInteractionSource() }

    val isPressed by interaction.collectIsPressedAsState()

    val view = LocalView.current
    LaunchedEffect(Unit) {
        snapshotFlow { isPressed }
            .drop(1)
            .collect {
                if (it) view.performHapticFeedback(HapticFeedbackConstantsCompat.KEYBOARD_PRESS)
            }
    }

    Box(
        modifier = Modifier
            .padding(8.dp)
            .size(64.dp)
            .hoverable(interaction)
            .clickable(
                interactionSource = interaction,
                indication = null,
                onClick = onClick,
            )
            .background(
                color,
                CircleShape,
            )
            .background(
                Brush.verticalGradient(
                    listOf(Color.Transparent, Color.Black.copy(alpha = .2f))
                ),
                CircleShape,
            )
            .offset { IntOffset(0, if (isPressed) -1 else -15) }
            .border(
                1.dp,
                Brush.verticalGradient(
                    listOf(Color.White.copy(alpha = .3f), Color.Transparent)
                ),
                CircleShape,
            )
            .background(
                color,
                CircleShape,
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = color.getTextColor()
        )

    }
}