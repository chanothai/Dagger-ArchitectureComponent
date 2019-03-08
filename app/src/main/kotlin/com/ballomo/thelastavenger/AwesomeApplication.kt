package com.ballomo.thelastavenger

import com.ballomo.thelastavenger.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class AwesomeApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = DaggerAppComponent.builder().create(this)
//    {
//        val component = DaggerAppComponent
//            .builder()
//            .application(this)
//            .build()
//
//        component.inject(this)
//        return component
//    }
}