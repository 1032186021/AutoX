package com.saltfish.assistant.domain.model

data class DeviceInfo(
    val id: Long = 0,
    val uuid: String = "",
    val name: String = "",
    val model: String = "",
    val brand: String = "",
    val androidVersion: String = "",
    val isRooted: Boolean = false,
    val availableMemory: String = "",
    val batteryLevel: Int = 0
)
