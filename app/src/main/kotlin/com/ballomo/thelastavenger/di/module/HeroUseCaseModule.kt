package com.ballomo.thelastavenger.di.module

import com.ballomo.shared.data.api.HeroApi
import com.ballomo.shared.data.repository.HeroRepo
import com.ballomo.thelastavenger.domain.LoadHeroUseCase
import com.ballomo.thelastavenger.domain.LoadHeroWithPageUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class HeroUseCaseModule {
    @Singleton
    @Provides
    fun provideHeroRepository(heroApi: HeroApi) = HeroRepo(heroApi)

    @Singleton
    @Provides
    fun provideLoadHeroWithPageUseCase(heroRepo: HeroRepo) = LoadHeroWithPageUseCase(heroRepo)
}