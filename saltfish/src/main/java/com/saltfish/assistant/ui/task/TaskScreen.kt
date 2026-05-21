package com.saltfish.assistant.ui.task

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.saltfish.assistant.domain.model.TaskEntity
import com.saltfish.assistant.domain.model.TaskStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskTopBar() {
    CenterAlignedTopAppBar(
        title = { Text("任务队列") },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.8f)
        )
    )
}

@Composable
fun TaskContent(viewModel: TaskViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("待执行", "已完成", "失败")

    val filteredTasks = remember(uiState.tasks, selectedTab) {
        when (selectedTab) {
            0 -> uiState.tasks.filter { it.status == TaskStatus.PENDING || it.status == TaskStatus.RUNNING }
            1 -> uiState.tasks.filter { it.status == TaskStatus.COMPLETED }
            2 -> uiState.tasks.filter { it.status == TaskStatus.FAILED || it.status == TaskStatus.CANCELLED }
            else -> emptyList()
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(selectedTabIndex = selectedTab) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = { Text(title) }
                )
            }
        }
        if (filteredTasks.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        Icons.Default.List,
                        contentDescription = null,
                        modifier = Modifier.size(48.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        "暂无任务",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filteredTasks, key = { it.id }) { task ->
                    TaskCard(
                        task = task,
                        onCancel = { viewModel.cancelTask(task.id) }
                    )
                }
            }
        }
    }
}

@Composable
private fun TaskCard(task: TaskEntity, onCancel: () -> Unit) {
    ElevatedCard(shape = MaterialTheme.shapes.medium) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(task.taskType, style = MaterialTheme.typography.titleSmall)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    "平台: ${task.platform} | 优先级: ${task.priority}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    statusText(task.status),
                    style = MaterialTheme.typography.labelMedium,
                    color = statusColor(task.status)
                )
                task.errorMessage?.let {
                    Text(
                        it,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            if (task.status == TaskStatus.PENDING || task.status == TaskStatus.RUNNING) {
                IconButton(onClick = onCancel) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "取消",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
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

private fun statusColor(status: TaskStatus) = when (status) {
    TaskStatus.PENDING -> Color(0xFFFFA000)
    TaskStatus.RUNNING -> Color(0xFF2196F3)
    TaskStatus.COMPLETED -> Color(0xFF4CAF50)
    TaskStatus.FAILED -> Color(0xFFF44336)
    TaskStatus.CANCELLED -> Color(0xFF9E9E9E)
}
