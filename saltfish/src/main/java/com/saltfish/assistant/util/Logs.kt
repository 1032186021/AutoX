package com.saltfish.assistant.util

import android.util.Log
import com.saltfish.assistant.BuildConfig

object Logs {
    @PublishedApi
    internal const val TAG = "Saltfish"

    inline fun verbose(module: String, message: () -> String) {
        if (BuildConfig.DEBUG) {
            Log.v(TAG, "[$module] ${message()}")
        }
    }

    inline fun debug(module: String, message: () -> String) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "[$module] ${message()}")
        }
    }

    inline fun info(module: String, message: () -> String) {
        if (BuildConfig.DEBUG) {
            Log.i(TAG, "[$module] ${message()}")
        }
    }

    inline fun warn(module: String, message: () -> String) {
        if (BuildConfig.DEBUG) {
            Log.w(TAG, "[$module] ${message()}")
        }
    }

    inline fun error(module: String, message: () -> String) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, "[$module] ${message()}")
        }
    }

    inline fun error(module: String, throwable: Throwable, message: () -> String) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, "[$module] ${message()}", throwable)
        }
    }
}
