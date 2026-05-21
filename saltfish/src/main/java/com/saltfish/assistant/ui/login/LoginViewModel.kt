package com.saltfish.assistant.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.saltfish.assistant.SaltfishApp
import com.saltfish.assistant.data.remote.ApiResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val captcha: String = "",
    val captchaBase64: String? = null,
    val captchaId: String? = null,
    val showCaptcha: Boolean = false,
    val rememberAccount: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val loginSuccess: Boolean = false,
    val isLoggedIn: Boolean = false
)

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val app = application as SaltfishApp
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    init {
        val prefs = app.preferencesManager
        _uiState.value = _uiState.value.copy(
            isLoggedIn = prefs.isLoggedIn(),
            rememberAccount = prefs.rememberAccount,
            username = if (prefs.rememberAccount) prefs.username else "",
            password = if (prefs.rememberAccount) prefs.password else ""
        )
    }

    fun updateUsername(value: String) {
        _uiState.value = _uiState.value.copy(username = value, errorMessage = null)
    }

    fun updatePassword(value: String) {
        _uiState.value = _uiState.value.copy(password = value, errorMessage = null)
    }

    fun updateCaptcha(value: String) {
        _uiState.value = _uiState.value.copy(captcha = value)
    }

    fun setRememberAccount(value: Boolean) {
        _uiState.value = _uiState.value.copy(rememberAccount = value)
    }

    fun loadCaptcha() {
        viewModelScope.launch {
            when (val result = app.apiClient.safeApiCall { app.apiClient.auth.getCaptcha() }) {
                is ApiResult.Success -> {
                    val captcha = result.data
                    _uiState.value = _uiState.value.copy(
                        captchaId = captcha.captchaId,
                        captchaBase64 = captcha.data,
                        showCaptcha = true
                    )
                }
                is ApiResult.Error -> {
                    _uiState.value = _uiState.value.copy(errorMessage = "获取验证码失败")
                }
            }
        }
    }

    fun login() {
        val state = _uiState.value
        if (state.username.isBlank()) {
            _uiState.value = state.copy(errorMessage = "请输入用户名")
            return
        }
        if (state.password.isBlank()) {
            _uiState.value = state.copy(errorMessage = "请输入密码")
            return
        }
        if (state.showCaptcha && state.captcha.isBlank()) {
            _uiState.value = state.copy(errorMessage = "请输入验证码")
            return
        }

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

            val body = JsonObject().apply {
                addProperty("username", state.username)
                addProperty("password", state.password)
                if (state.showCaptcha) {
                    addProperty("verifyCode", state.captcha)
                    addProperty("captchaId", state.captchaId)
                }
            }

            val result = if (state.showCaptcha) {
                app.apiClient.safeApiCall { app.apiClient.auth.loginWithCaptcha(body) }
            } else {
                app.apiClient.safeApiCall { app.apiClient.auth.login(body) }
            }

            when (result) {
                is ApiResult.Success -> {
                    val auth = result.data
                    val prefs = app.preferencesManager
                    prefs.token = auth.token
                    prefs.refreshToken = auth.refreshToken

                    if (state.rememberAccount) {
                        prefs.username = state.username
                        prefs.password = state.password
                        prefs.rememberAccount = true
                    } else {
                        prefs.username = ""
                        prefs.password = ""
                        prefs.rememberAccount = false
                    }

                    fetchUserInfo()
                    _uiState.value = _uiState.value.copy(isLoading = false, loginSuccess = true)
                }
                is ApiResult.Error -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = result.message
                    )
                    if (state.showCaptcha) loadCaptcha()
                }
            }
        }
    }

    private suspend fun fetchUserInfo() {
        when (val result = app.apiClient.safeApiCall { app.apiClient.user.getUserInfo() }) {
            is ApiResult.Success -> {
                val data = result.data
                val prefs = app.preferencesManager
                prefs.nickName = data.get("nickName")?.asString
                prefs.userInfoJson = data.toString()
            }
            is ApiResult.Error -> { /* 获取用户信息失败不影响登录 */ }
        }
    }

    fun logout() {
        app.preferencesManager.logout()
        _uiState.value = LoginUiState(isLoggedIn = false)
    }
}
