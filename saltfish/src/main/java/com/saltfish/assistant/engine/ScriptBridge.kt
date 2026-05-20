package com.saltfish.assistant.engine

import com.saltfish.assistant.domain.model.TaskResult
import com.stardust.autojs.engine.ScriptEngine
import com.stardust.autojs.engine.ScriptEngineManager
import com.stardust.autojs.script.JavaScriptSource
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.json.JSONObject

class ScriptBridge(
    private val engineManager: ScriptEngineManager,
    private val scriptManager: ScriptManager
) {
    private val _taskState = MutableStateFlow<TaskExecutionState>(TaskExecutionState.Idle)
    val taskState: StateFlow<TaskExecutionState> = _taskState

    private var currentDeferred: CompletableDeferred<TaskResult>? = null

    /**
     * Execute an automation task by running the adapter script in the Rhino engine.
     * Script runs synchronously in the calling thread.
     */
    suspend fun executeTask(
        platform: String,
        taskType: String,
        params: JSONObject
    ): TaskResult {
        val deferred = CompletableDeferred<TaskResult>()
        currentDeferred = deferred

        val scriptContent = scriptManager.getAdapterScript(platform)
            ?: return TaskResult.Error("Adapter script not found: $platform")

        val engine = engineManager.createEngineOfSourceOrThrow(
            JavaScriptSource("adapter_$platform", scriptContent)
        )
        try {
            engine.put("__scriptBridge__", this)
            engine.put("__platform__", platform)
            engine.put("__taskType__", taskType)
            engine.put("__params__", params.toString())
            engine.init()

            _taskState.value = TaskExecutionState.Running(platform, taskType, 0)

            val result = engine.execute(
                JavaScriptSource("adapter_$platform", scriptContent)
            )

            val finalResult = parseResult(result)
            deferred.complete(finalResult)
            _taskState.value = TaskExecutionState.Idle
            return finalResult
        } catch (e: Exception) {
            val errorResult = TaskResult.Error(e.message ?: "Script execution failed")
            deferred.complete(errorResult)
            _taskState.value = TaskExecutionState.Idle
            return errorResult
        } finally {
            engine.destroy()
        }
    }

    // Called by adapter scripts via Rhino binding
    fun onTaskProgress(message: String) {
        _taskState.value = (_taskState.value as? TaskExecutionState.Running)?.let {
            it.copy(progress = it.progress + 1)
        } ?: TaskExecutionState.Running("", "", 0)
    }

    fun onTaskLog(message: String, level: String = "info") {
        // Log forwarding to UI
    }

    fun onTaskComplete(data: String) {
        val current = currentDeferred
        currentDeferred = null
        current?.complete(TaskResult.Success(JSONObject(data)))
    }

    fun onTaskError(error: String) {
        val current = currentDeferred
        currentDeferred = null
        current?.complete(TaskResult.Error(error))
    }

    private fun parseResult(raw: Any?): TaskResult {
        return when (raw) {
            is String -> TaskResult.Success(JSONObject(raw))
            is JSONObject -> TaskResult.Success(raw)
            null -> TaskResult.Success(JSONObject())
            else -> TaskResult.Success(JSONObject(raw.toString()))
        }
    }
}

sealed class TaskExecutionState {
    object Idle : TaskExecutionState()
    data class Running(
        val platform: String,
        val taskType: String,
        val progress: Int
    ) : TaskExecutionState()
}
