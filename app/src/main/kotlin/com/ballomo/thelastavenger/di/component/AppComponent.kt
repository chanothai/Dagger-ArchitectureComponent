package com.ballomo.thelastavenger.di.component

import com.ballomo.thelastavenger.AwesomeApplication
import com.ballomo.thelastavenger.di.module.ActivityModule
import com.ballomo.thelastavenger.di.module.AppModule
import com.ballomo.thelastavenger.di.module.NetWorkModule
import com.ballomo.thelastavenger.di.module.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        AppModule::class,
        ViewModelModule::class,
        NetWorkModule::class
    ]
)

interface AppComponent : AndroidInjector<AwesomeApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<AwesomeApplication>()
}