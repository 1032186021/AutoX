package com.saltfish.assistant.domain.model

import org.json.JSONObject

sealed class TaskResult {
    data class Success(val data: JSONObject = JSONObject()) : TaskResult()
    data class Error(val message: String) : TaskResult()
}
