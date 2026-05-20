package com.saltfish.assistant.ui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.saltfish.assistant.SaltfishApp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
    val context = LocalContext.current
    val app = context.applicationContext as SaltfishApp
    var autoUpgrade by remember { mutableStateOf(app.preferencesManager.autoUpgrade) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("设置") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("更新设置", style = MaterialTheme.typography.titleMedium)
            ElevatedCard(shape = MaterialTheme.shapes.medium) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("自动更新", style = MaterialTheme.typography.bodyLarge)
                    Switch(
                        checked = autoUpgrade,
                        onCheckedChange = {
                            autoUpgrade = it
                            app.preferencesManager.autoUpgrade = it
                        }
                    )
                }
            }

            HorizontalDivider()

            Text("缓存管理", style = MaterialTheme.typography.titleMedium)
            OutlinedButton(
                onClick = {
                    context.cacheDir.deleteRecursively()
                    context.cacheDir.mkdirs()
                },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.small
            ) {
                Text("清除缓存")
            }

            HorizontalDivider()

            Text("关于", style = MaterialTheme.typography.titleMedium)
            ElevatedCard(shape = MaterialTheme.shapes.medium) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("咸鱼助手 v1.0.0", style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        "基于 AutoX 引擎",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}
