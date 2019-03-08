package com.ballomo.thelastavenger.di.module

import com.ballomo.thelastavenger.ui.hero.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
//    @ContributesAndroidInjector(modules = [FragmentModule::class])
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}