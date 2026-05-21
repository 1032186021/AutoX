package com.saltfish.assistant.data.remote

import com.google.gson.GsonBuilder
import com.saltfish.assistant.BuildConfig
import com.saltfish.assistant.util.Logs
import com.saltfish.assistant.data.local.PreferencesManager
import com.saltfish.assistant.data.remote.api.*
import okhttp3.OkHttpClient
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class ApiClient(private val prefs: PreferencesManager) {

    var onUnauthorized: (() -> Unit)? = null

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val original = chain.request()
                val builder = original.newBuilder()
                prefs.token?.let { builder.addHeader("Authorization", it) }
                prefs.deviceId?.let { builder.addHeader("DeviceId", it) }
                prefs.uuid?.let { builder.addHeader("UUID", it) }
                builder.addHeader("AppVersion", BuildConfig.VERSION_NAME)
                builder.addHeader("ScriptVersion", BuildConfig.SCRIPT_VERSION)
                builder.addHeader("AppId", "1")
                chain.proceed(builder.build())
            }
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(prefs.baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    val auth: AuthApi by lazy { retrofit.create(AuthApi::class.java) }
    val user: UserApi by lazy { retrofit.create(UserApi::class.java) }
    val device: DeviceApi by lazy { retrofit.create(DeviceApi::class.java) }
    val account: AccountApi by lazy { retrofit.create(AccountApi::class.java) }
    val goods: GoodsApi by lazy { retrofit.create(GoodsApi::class.java) }
    val post: PostApi by lazy { retrofit.create(PostApi::class.java) }
    val order: OrderApi by lazy { retrofit.create(OrderApi::class.java) }
    val product: ProductApi by lazy { retrofit.create(ProductApi::class.java) }
    val cashback: CashbackApi by lazy { retrofit.create(CashbackApi::class.java) }
    val receipt: ReceiptApi by lazy { retrofit.create(ReceiptApi::class.java) }
    val copywriting: CopywritingApi by lazy { retrofit.create(CopywritingApi::class.java) }
    val task: TaskApi by lazy { retrofit.create(TaskApi::class.java) }
    val config: ConfigApi by lazy { retrofit.create(ConfigApi::class.java) }
    val bug: BugApi by lazy { retrofit.create(BugApi::class.java) }

    suspend fun <T> safeApiCall(call: suspend () -> ApiResponse<T>): ApiResult<T> {
        Logs.debug("ApiClient") { "safeApiCall invoked" }
        return try {
            val response = call()
            if (response.code == 1000) {
                ApiResult.Success(response.data!!)
            } else {
                ApiResult.Error(response.code, response.message)
            }
        } catch (e: HttpException) {
            when (e.code()) {
                401 -> {
                    onUnauthorized?.invoke()
                    ApiResult.Error(401, "登录失效，请先登录")
                }
                403 -> ApiResult.Error(403, "无操作权限")
                else -> ApiResult.Error(e.code(), e.message())
            }
        } catch (e: IOException) {
            ApiResult.Error(-1, "网络连接失败，请检查网络")
        } catch (e: Exception) {
            ApiResult.Error(-1, e.message ?: "请求失败")
        }
    }
}
