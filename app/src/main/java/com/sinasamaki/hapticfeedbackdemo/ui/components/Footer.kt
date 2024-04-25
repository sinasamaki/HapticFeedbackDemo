package com.sinasamaki.hapticfeedbackdemo.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.view.HapticFeedbackConstantsCompat

@Composable
fun Footer(modifier: Modifier = Modifier) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp),
        contentAlignment = Alignment.Center,
    ) {

        val view = LocalView.current

        var isHapticsOn by remember { mutableStateOf(view.isHapticFeedbackEnabled) }

        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .clickable {
                    view.performHapticFeedback(HapticFeedbackConstantsCompat.REJECT)
                    view.isHapticFeedbackEnabled = !view.isHapticFeedbackEnabled
                    isHapticsOn = view.isHapticFeedbackEnabled
                    view.performHapticFeedback(HapticFeedbackConstantsCompat.CONFIRM)
                }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = "Turn ${if (isHapticsOn) "off" else "on"} haptic feedback")
            Spacer(modifier = Modifier.width(16.dp))
            Switch(checked = isHapticsOn, onCheckedChange = null)
        }

    }

}