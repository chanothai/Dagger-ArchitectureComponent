package com.ballomo.thelastavenger.di.module

import com.ballomo.shared.di.FragmentScoped
import com.ballomo.thelastavenger.ui.hero.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment
}