package com.saltfish.assistant.ui.guide

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.FormatListBulleted
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.saltfish.assistant.SaltfishApp

private data class GuidePage(
    val icon: ImageVector,
    val title: String,
    val description: String
)

private val pages = listOf(
    GuidePage(
        icon = Icons.Filled.Terminal,
        title = "控制台",
        description = "查看设备状态，管理权限开关，连接服务器\n实时掌握设备运行情况"
    ),
    GuidePage(
        icon = Icons.Filled.Android,
        title = "App自动化",
        description = "自动化控制闲鱼、拼多多等应用\n支持商品发布、订单管理等功能"
    ),
    GuidePage(
        icon = Icons.Filled.FormatListBulleted,
        title = "任务队列",
        description = "管理自动化任务，查看执行进度\n支持暂停、清空、重启队列操作"
    ),
    GuidePage(
        icon = Icons.Filled.Description,
        title = "运行日志",
        description = "实时查看脚本运行日志\n支持搜索和导出，方便排查问题"
    )
)

private val Primary = Color(0xFF6750A4)
private val InactiveDot = Color(0xFFB0ACB5)
private val SurfaceColor = Color(0xFFFFFBFE)

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun GuideScreen(onDone: () -> Unit) {
    val pagerState = rememberPagerState()

    val app = androidx.compose.ui.platform.LocalContext.current
        .applicationContext as SaltfishApp

    val density = LocalDensity.current
    val statusBarH = with(density) {
        WindowInsets.statusBars.getTop(density).toDp()
    }

    LaunchedEffect(Unit) {
        app.preferencesManager.guideShown = true
    }

    Scaffold(
        containerColor = SurfaceColor
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(SurfaceColor)
        ) {
            // Skip button — top right
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = statusBarH + 16.dp, end = 16.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                if (pagerState.currentPage < pages.size - 1) {
                    TextButton(onClick = onDone) {
                        Text("跳过", color = Color(0xFF79747E), fontSize = 14.sp)
                    }
                }
            }

            // HorizontalPager
            HorizontalPager(
                count = pages.size,
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) { index ->
                GuidePageContent(pages[index])
            }

            // Dot indicators
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp, top = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                pages.indices.forEach { index ->
                    val color by animateColorAsState(
                        targetValue = if (index == pagerState.currentPage) Primary else InactiveDot,
                        animationSpec = tween(300)
                    )
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 6.dp)
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(color)
                    )
                }
            }

            // Bottom button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, bottom = 32.dp, top = 16.dp)
                    .height(48.dp)
            ) {
                if (pagerState.currentPage == pages.size - 1) {
                    Button(
                        onClick = onDone,
                        modifier = Modifier.fillMaxSize(),
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Primary,
                            contentColor = Color.White
                        )
                    ) {
                        Text("开始使用", fontSize = 16.sp)
                    }
                }
            }
        }
    }
}

@Composable
private fun GuidePageContent(page: GuidePage) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 40.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = page.icon,
            contentDescription = null,
            modifier = Modifier
                .padding(24.dp)
                .size(80.dp),
            tint = Primary
        )
        Text(
            text = page.title,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1C1B1F),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = page.description,
            fontSize = 14.sp,
            color = Color(0xFF49454F),
            textAlign = TextAlign.Center,
            lineHeight = 22.sp
        )
    }
}
