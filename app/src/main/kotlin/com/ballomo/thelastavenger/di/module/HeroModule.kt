package com.ballomo.thelastavenger.di.module

import androidx.lifecycle.ViewModel
import com.ballomo.shared.di.ViewModelKey
import com.ballomo.thelastavenger.ui.hero.all.HeroViewModel
import com.ballomo.thelastavenger.ui.hero.paging.HeroPagingViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HeroModule {
    @Binds
    @IntoMap
    @ViewModelKey(HeroPagingViewModel::class)
    abstract fun bindHeroPagingViewModel(viewModel: HeroPagingViewModel): ViewModel
}