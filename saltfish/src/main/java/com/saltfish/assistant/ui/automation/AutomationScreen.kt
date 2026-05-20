package com.saltfish.assistant.ui.automation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.saltfish.assistant.engine.SchedulerState
import com.saltfish.assistant.engine.TaskExecutionState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AutomationScreen(viewModel: AutomationViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val logs by viewModel.logLines.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("自动化控制") },
                actions = {
                    IconButton(onClick = { viewModel.clearLogs() }) {
                        Icon(Icons.Default.Delete, contentDescription = "清空日志")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Service status
            ElevatedCard(shape = MaterialTheme.shapes.medium) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("脚本服务", style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Surface(
                                modifier = Modifier.size(8.dp),
                                shape = MaterialTheme.shapes.extraSmall,
                                color = if (uiState.isServiceRunning)
                                    MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.error
                            ) {}
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                if (uiState.isServiceRunning) "运行中" else "已停止",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                    if (uiState.isServiceRunning) {
                        OutlinedButton(
                            onClick = { viewModel.stopService() },
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = MaterialTheme.colorScheme.error
                            )
                        ) {
                            Icon(Icons.Default.Close, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("停止")
                        }
                    } else {
                        Button(onClick = { viewModel.startService() }) {
                            Icon(Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("启动")
                        }
                    }
                }
            }

            // Scheduler + Task state
            ElevatedCard(shape = MaterialTheme.shapes.medium) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("调度器", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(32.dp)) {
                        StatusItem("队列", "${uiState.schedulerState.queueSize}")
                        StatusItem("状态", if (uiState.schedulerState.isRunning) "执行中" else "空闲")
                    }
                    when (val ts = uiState.taskState) {
                        is TaskExecutionState.Idle -> {
                            Text("空闲", style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                        is TaskExecutionState.Running -> {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("${ts.platform} / ${ts.taskType}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.primary)
                            Text("进度: ${ts.progress} 步",
                                style = MaterialTheme.typography.bodySmall)
                            Spacer(modifier = Modifier.height(4.dp))
                            LinearProgressIndicator(
                                progress = (ts.progress % 100) / 100f,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }

            Divider()

            // Platform selector
            Text("选择平台", style = MaterialTheme.typography.titleMedium)
            if (uiState.availablePlatforms.isEmpty()) {
                Text("暂无可用的适配器脚本",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant)
            } else {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                    uiState.availablePlatforms.keys.take(6).forEach { platform ->
                        FilterChip(
                            selected = platform == uiState.selectedPlatform,
                            onClick = { viewModel.selectPlatform(platform) },
                            label = { Text(platformName(platform), style = MaterialTheme.typography.labelMedium) }
                        )
                    }
                }
            }

            // Task type selector
            Text("任务类型", style = MaterialTheme.typography.titleMedium)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                AutomationViewModel.TASK_TYPES.take(5).forEach { (type, label) ->
                    FilterChip(
                        selected = type == uiState.selectedTaskType,
                        onClick = { viewModel.selectTaskType(type) },
                        label = { Text(label, style = MaterialTheme.typography.labelMedium) }
                    )
                }
            }

            // Task params
            Text("任务参数 (JSON)", style = MaterialTheme.typography.titleMedium)
            OutlinedTextField(
                value = uiState.taskParams,
                onValueChange = { viewModel.updateParams(it) },
                modifier = Modifier.fillMaxWidth().heightIn(min = 80.dp),
                isError = uiState.paramsError != null,
                maxLines = 5
            )
            uiState.paramsError?.let {
                Text(it, color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall)
            }

            // Submit
            Button(
                onClick = { viewModel.submitTask() },
                modifier = Modifier.fillMaxWidth().height(48.dp),
                enabled = uiState.submitEnabled,
                shape = MaterialTheme.shapes.small
            ) {
                Icon(Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("提交任务")
            }

            if (!uiState.submitEnabled && uiState.selectedPlatform.isNotEmpty()) {
                Text(
                    when {
                        !uiState.isServiceRunning -> "请先启动脚本服务"
                        uiState.selectedTaskType.isEmpty() -> "请选择任务类型"
                        uiState.paramsError != null -> "请修正参数错误"
                        else -> ""
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error
                )
            }

            Divider()

            // Log section
            Text("运行日志", style = MaterialTheme.typography.titleMedium)
            ElevatedCard(
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.heightIn(min = 120.dp, max = 300.dp)
            ) {
                if (logs.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxWidth().padding(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("暂无日志", style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                } else {
                    val listState = rememberLazyListState()
                    LaunchedEffect(logs.size) {
                        if (logs.isNotEmpty()) listState.animateScrollToItem(logs.size - 1)
                    }
                    LazyColumn(
                        state = listState,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        items(logs) { log ->
                            Text(
                                text = log,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontFamily = FontFamily.Monospace
                                ),
                                modifier = Modifier.padding(vertical = 1.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun StatusItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary)
        Text(label, style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

private fun platformName(packageName: String): String = when (packageName) {
    "com.taobao.idlefish" -> "闲鱼"
    "com.xunmeng.pinduoduo" -> "拼多多"
    "com.wuba.zhuanzhuan" -> "转转"
    "com.sina.weibo" -> "微博"
    "com.xingin.xhs" -> "小红书"
    "com.duoduo.tuanzhang" -> "多多团长"
    else -> packageName
}
