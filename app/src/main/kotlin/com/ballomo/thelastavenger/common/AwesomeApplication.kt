package com.ballomo.thelastavenger.common

import com.ballomo.thelastavenger.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class AwesomeApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = DaggerAppComponent.builder().create(this)
}