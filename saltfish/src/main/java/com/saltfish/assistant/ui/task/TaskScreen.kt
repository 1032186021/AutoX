package com.saltfish.assistant.ui.task

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.saltfish.assistant.domain.model.TaskEntity
import com.saltfish.assistant.domain.model.TaskStatus

@Composable
fun TaskScreen(viewModel: TaskViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("任务队列") }
            )
        }
    ) { padding ->
        if (uiState.tasks.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                Text("暂无任务", color = Color.Gray, fontSize = 16.sp)
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(uiState.tasks) { task ->
                    TaskItem(task = task, onCancel = { viewModel.cancelTask(task.id) })
                }
            }
        }
    }
}

@Composable
fun TaskItem(task: TaskEntity, onCancel: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = task.taskType,
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "平台: ${task.platform} | 优先级: ${task.priority}",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Text(
                    text = "状态: ${statusText(task.status)}",
                    fontSize = 13.sp,
                    color = statusColor(task.status)
                )
                task.errorMessage?.let {
                    Text(
                        text = it,
                        fontSize = 12.sp,
                        color = Color(0xFFF44336),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            if (task.status == TaskStatus.PENDING || task.status == TaskStatus.RUNNING) {
                IconButton(onClick = onCancel) {
                    Icon(Icons.Default.Delete, contentDescription = "取消", tint = Color.Gray)
                }
            }
        }
    }
}

private fun statusText(status: TaskStatus): String = when (status) {
    TaskStatus.PENDING -> "等待中"
    TaskStatus.RUNNING -> "执行中"
    TaskStatus.COMPLETED -> "已完成"
    TaskStatus.FAILED -> "失败"
    TaskStatus.CANCELLED -> "已取消"
}

private fun statusColor(status: TaskStatus): Color = when (status) {
    TaskStatus.PENDING -> Color(0xFFFFA000)
    TaskStatus.RUNNING -> Color(0xFF2196F3)
    TaskStatus.COMPLETED -> Color(0xFF4CAF50)
    TaskStatus.FAILED -> Color(0xFFF44336)
    TaskStatus.CANCELLED -> Color(0xFF9E9E9E)
}
