package com.sinasamaki.hapticfeedbackdemo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DemoWindow(
    title: String = "Title",
    description: String = "Description",
    content: @Composable () -> Unit,
) {

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .padding(bottom = 12.dp)
                .widthIn(max = 400.dp)
        ) {

            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text = description
            )
            Spacer(modifier = Modifier.height(4.dp))


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(175.dp)
                    .shadow(
                        elevation = 16.dp,
                        shape = RoundedCornerShape(4.dp),
                        spotColor = Color.Black.copy(alpha = .4f)
                    )
                    .drawBehind {
                        drawRect(
                            color = Color.Black
                        )
                    }
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(4.dp)
                    )
            ) {
                content()
            }
        }
    }

}