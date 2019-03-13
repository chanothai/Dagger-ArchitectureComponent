package com.ballomo.thelastavenger.di.module

import com.ballomo.shared.di.ActivityScoped
import com.ballomo.thelastavenger.ui.hero.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = [HeroModule::class])
    internal abstract fun contributeMainActivity(): MainActivity

}