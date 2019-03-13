package com.ballomo.thelastavenger.network

import com.ballomo.shared.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class OKHttpClientFactory {
    fun getInstant(): OkHttpClient {
        val client = OkHttpClient.Builder()
            .connectTimeout(NetworkConstant.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(NetworkConstant.READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(NetworkConstant.WRITE_TIMEOUT, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val bodyLogging = HttpLoggingInterceptor()
            bodyLogging.level = HttpLoggingInterceptor.Level.BODY
            client.addNetworkInterceptor(bodyLogging)
            client.addInterceptor(bodyLogging)
        }

        return client.build()
    }
}

object NetworkConstant {
    const val CONNECT_TIMEOUT: Long = 30
    const val READ_TIMEOUT: Long = 60
    const val WRITE_TIMEOUT: Long = 120
}