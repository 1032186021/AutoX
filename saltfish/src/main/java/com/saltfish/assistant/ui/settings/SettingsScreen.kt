package com.saltfish.assistant.ui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saltfish.assistant.SaltfishApp

@Composable
fun SettingsScreen() {
    val context = LocalContext.current
    val app = context.applicationContext as SaltfishApp
    var baseUrl by remember { mutableStateOf(app.preferencesManager.baseUrl) }
    var wsUrl by remember { mutableStateOf(app.preferencesManager.wsUrl) }
    var autoUpgrade by remember { mutableStateOf(app.preferencesManager.autoUpgrade) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("设置") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Server config
            Text("服务器配置", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            OutlinedTextField(
                value = baseUrl,
                onValueChange = { baseUrl = it },
                label = { Text("API 地址") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = wsUrl,
                onValueChange = { wsUrl = it },
                label = { Text("WebSocket 地址") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Divider()

            // Update settings
            Text("更新设置", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Row(modifier = Modifier.fillMaxWidth()) {
                Text("自动更新", modifier = Modifier.weight(1f), fontSize = 15.sp)
                Switch(
                    checked = autoUpgrade,
                    onCheckedChange = {
                        autoUpgrade = it
                        app.preferencesManager.autoUpgrade = it
                    }
                )
            }

            Divider()

            // Debug section
            Text("调试", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Button(
                onClick = {
                    app.preferencesManager.baseUrl = baseUrl
                    app.preferencesManager.wsUrl = wsUrl
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("保存设置")
            }

            OutlinedButton(
                onClick = {
                    context.cacheDir.deleteRecursively()
                    context.cacheDir.mkdirs()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("清除缓存")
            }

            Divider()

            Text("关于", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text("咸鱼助手 v1.0.0", fontSize = 14.sp)
            Text("基于 AutoX 引擎", fontSize = 12.sp, color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f))
        }
    }
}
