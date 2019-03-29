package com.ballomo.thelastavenger.network

import com.ballomo.shared.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class OKHttpClientFactory {
    fun getInstant(): OkHttpClient {
        val client = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val bodyLogging = HttpLoggingInterceptor()
            bodyLogging.level = HttpLoggingInterceptor.Level.BODY
            client.addInterceptor(bodyLogging)
        }

        return client.build()
    }
}