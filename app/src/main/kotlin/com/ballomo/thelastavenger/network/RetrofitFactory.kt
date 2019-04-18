package com.ballomo.thelastavenger.network

import com.ballomo.thelastavenger.BuildConfig
import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RetrofitFactory (
    private val okHttpClientFactory: OKHttpClientFactory) {

    fun retrofitBuilder(): Retrofit {
        val gson = GsonBuilder().setLenient().create()
        val rxJava2CallAdapterFactory = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())

        return Retrofit.Builder()
            .client(okHttpClientFactory.getInstant())
            .baseUrl(BuildConfig.SERVICE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .build()
    }
}