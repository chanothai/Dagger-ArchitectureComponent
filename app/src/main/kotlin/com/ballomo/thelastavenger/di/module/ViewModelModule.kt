package com.ballomo.thelastavenger.di.module

import androidx.lifecycle.ViewModelProvider
import com.ballomo.shared.di.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}