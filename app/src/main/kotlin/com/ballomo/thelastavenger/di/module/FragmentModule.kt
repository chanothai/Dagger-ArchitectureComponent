package com.ballomo.thelastavenger.di.module

import com.ballomo.shared.di.FragmentScoped
import com.ballomo.thelastavenger.ui.hero.paging.HeroPagingFragment
import com.ballomo.thelastavenger.ui.hero.all.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeHeroPagingFragment(): HeroPagingFragment
}