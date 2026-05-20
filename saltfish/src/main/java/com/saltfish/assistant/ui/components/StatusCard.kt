package com.saltfish.assistant.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StatusCard(
    title: String,
    value: String,
    statusColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = 2.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, fontSize = 13.sp, color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f))
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    modifier = Modifier.size(8.dp),
                    shape = MaterialTheme.shapes.small,
                    color = statusColor
                ) {}
                Spacer(modifier = Modifier.width(8.dp))
                Text(value, fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }
        }
    }
}
