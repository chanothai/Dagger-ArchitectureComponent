package com.ballomo.thelastavenger.di.module

import com.ballomo.thelastavenger.network.OKHttpClientFactory
import com.ballomo.thelastavenger.network.RetrofitFactory
import com.ballomo.thelastavenger.ui.hero.api.HeroApi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetWorkModule {
    @Provides
    @Reusable
    internal fun provideHeroApi(retrofit: Retrofit): HeroApi = retrofit.create(HeroApi::class.java)

    @Provides
    @Singleton
    fun provideHttpClientFactory(): OKHttpClientFactory = OKHttpClientFactory()

    @Provides
    @Reusable
    internal fun provideRetrofitFactory(okHttpClientFactory: OKHttpClientFactory): Retrofit = RetrofitFactory(okHttpClientFactory).retrofitBuilder()
}