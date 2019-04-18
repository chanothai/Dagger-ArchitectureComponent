package com.ballomo.thelastavenger.di.module

import android.content.Context
import com.ballomo.thelastavenger.common.AwesomeApplication
import com.ballomo.thelastavenger.util.UserPreference
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    fun provideContext(application: AwesomeApplication): Context = application.applicationContext

    @Singleton
    @Provides
    fun provideUserPreference(context: Context) = UserPreference(context)
}