package com.saltfish.assistant.data.remote

import com.saltfish.assistant.BuildConfig

object ApiConfig {
    val API_BASE_URL: String get() = BuildConfig.API_BASE_URL
    val WS_URL: String get() = BuildConfig.WS_URL
}
