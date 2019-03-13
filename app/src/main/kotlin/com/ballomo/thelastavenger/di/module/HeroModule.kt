package com.ballomo.thelastavenger.di.module

import androidx.lifecycle.ViewModel
import com.ballomo.shared.di.ViewModelKey
import com.ballomo.thelastavenger.ui.hero.HeroViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HeroModule {
    @Binds
    @IntoMap
    @ViewModelKey(HeroViewModel::class)
    abstract fun bindHeroViewModel(viewModel: HeroViewModel): ViewModel
}