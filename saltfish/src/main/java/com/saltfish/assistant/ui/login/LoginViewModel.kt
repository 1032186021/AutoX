package com.saltfish.assistant.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.saltfish.assistant.SaltfishApp
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
            try {
                val response = app.apiClient.api.getCaptcha()
                val data = response.getAsJsonObject("data")
                val id = data?.get("captchaId")?.asString
                val base64 = data?.get("data")?.asString
                _uiState.value = _uiState.value.copy(
                    captchaId = id,
                    captchaBase64 = base64,
                    showCaptcha = true
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(errorMessage = "获取验证码失败")
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
            try {
                val body = JsonObject().apply {
                    addProperty("username", state.username)
                    addProperty("password", state.password)
                    if (state.showCaptcha) {
                        addProperty("verifyCode", state.captcha)
                        addProperty("captchaId", state.captchaId)
                    }
                }

                val response = if (state.showCaptcha) {
                    app.apiClient.api.loginWithCaptcha(body)
                } else {
                    app.apiClient.api.login(body)
                }

                val code = response.get("code")?.asInt ?: -1
                if (code == 1000) {
                    val data = response.getAsJsonObject("data")
                    val token = data?.get("token")?.asString
                    val refreshToken = data?.get("refreshToken")?.asString
                    val expire = data?.get("expire")?.asLong ?: 0L
                    val refreshExpire = data?.get("refreshExpire")?.asLong ?: 0L

                    val prefs = app.preferencesManager
                    prefs.token = token
                    prefs.refreshToken = refreshToken

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
                } else {
                    val message = response.get("message")?.asString ?: "登录失败"
                    _uiState.value = _uiState.value.copy(isLoading = false, errorMessage = message)
                    if (state.showCaptcha) loadCaptcha()
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "网络连接失败"
                )
            }
        }
    }

    private suspend fun fetchUserInfo() {
        try {
            val response = app.apiClient.api.getUserInfo()
            val code = response.get("code")?.asInt ?: -1
            if (code == 1000) {
                val data = response.getAsJsonObject("data")
                data?.let {
                    val prefs = app.preferencesManager
                    prefs.nickName = it.get("nickName")?.asString
                    prefs.userInfoJson = it.toString()
                }
            }
        } catch (_: Exception) { }
    }

    fun logout() {
        app.preferencesManager.logout()
        _uiState.value = LoginUiState(isLoggedIn = false)
    }
}
