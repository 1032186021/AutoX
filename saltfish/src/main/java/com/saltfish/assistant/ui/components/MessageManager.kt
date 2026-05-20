package com.saltfish.assistant.ui.components

import android.os.Handler
import android.os.Looper
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saltfish.assistant.ui.theme.StatusBlue
import com.saltfish.assistant.ui.theme.StatusGreen
import com.saltfish.assistant.ui.theme.StatusOrange
import com.saltfish.assistant.ui.theme.StatusRed

enum class MessageType(val tint: Color, val icon: ImageVector, val label: String) {
    Success(StatusGreen, Icons.Default.CheckCircle, "成功"),
    Error(StatusRed, Icons.Filled.Warning, "错误"),
    Warning(StatusOrange, Icons.Filled.Warning, "警告"),
    Info(StatusBlue, Icons.Default.Info, "信息")
}

enum class MessagePosition { Top, Bottom }

data class MessageItem(
    val id: Long,
    val text: String,
    val type: MessageType,
    val position: MessagePosition = MessagePosition.Bottom
)

object MessageManager {
    private val _messages = mutableStateListOf<MessageItem>()
    val messages: List<MessageItem> get() = _messages

    private var nextId = 0L
    private val handler = Handler(Looper.getMainLooper())

    fun show(text: String, type: MessageType, position: MessagePosition = MessagePosition.Bottom) {
        val id = nextId++
        _messages.add(MessageItem(id, text, type, position))
        handler.postDelayed({ dismiss(id) }, 3000)
    }

    fun success(text: String, position: MessagePosition = MessagePosition.Bottom) =
        show(text, MessageType.Success, position)

    fun error(text: String, position: MessagePosition = MessagePosition.Bottom) =
        show(text, MessageType.Error, position)

    fun warning(text: String, position: MessagePosition = MessagePosition.Bottom) =
        show(text, MessageType.Warning, position)

    fun info(text: String, position: MessagePosition = MessagePosition.Bottom) =
        show(text, MessageType.Info, position)

    fun dismiss(id: Long) {
        _messages.removeAll { it.id == id }
    }
}

@Composable
fun MessageHost() {
    val messages = MessageManager.messages
    val topMessages = remember(messages) { messages.filter { it.position == MessagePosition.Top } }
    val bottomMessages = remember(messages) { messages.filter { it.position == MessagePosition.Bottom } }

    Box(modifier = Modifier.fillMaxSize()) {
        // Top messages
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 24.dp)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            topMessages.forEach { msg ->
                MessageCard(msg)
            }
        }

        // Bottom messages
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            bottomMessages.reversed().forEach { msg ->
                MessageCard(msg)
            }
        }
    }
}

@Composable
private fun MessageCard(item: MessageItem) {
    AnimatedVisibility(
        visible = true,
        enter = fadeIn() + slideInVertically { if (item.position == MessagePosition.Bottom) it else -it },
        exit = fadeOut() + slideOutVertically { if (item.position == MessagePosition.Bottom) it else -it }
    ) {
        Row(
            modifier = Modifier
                .shadow(6.dp, RoundedCornerShape(12.dp))
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = item.type.icon,
                contentDescription = item.type.label,
                tint = item.type.tint,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = item.text,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF333333)
            )
        }
    }
}
