package com.saltfish.assistant.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PermissionItem(
    label: String,
    isGranted: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(8.dp),
                shape = MaterialTheme.shapes.small,
                color = if (isGranted) {
                    Color(0xFF4CAF50)
                } else {
                    Color(0xFFFF5722)
                }
            ) {}
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = label,
                modifier = Modifier.weight(1f),
                fontSize = 15.sp
            )
            Text(
                text = if (isGranted) "已授权" else "未授权",
                fontSize = 13.sp,
                color = if (isGranted) {
                    Color(0xFF4CAF50)
                } else {
                    Color(0xFFFF5722)
                }
            )
        }
    }
}
