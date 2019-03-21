package com.ballomo.thelastavenger.di.module

import android.content.Context
import com.ballomo.shared.domain.hero.LoadHeroUseCase
import com.ballomo.shared.data.api.HeroApi
import com.ballomo.shared.data.repository.HeroRepo
import com.ballomo.thelastavenger.AwesomeApplication
import com.ballomo.thelastavenger.ui.hero.HeroViewModel
import com.ballomo.thelastavenger.util.UserPreference
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    fun provideContext(application:AwesomeApplication): Context = application.applicationContext

    @Singleton
    @Provides
    fun provideUserPreference(context: Context) = UserPreference(context)

    @Singleton
    @Provides
    fun provideLoadHeroUseCase(heroRepo: HeroRepo) = LoadHeroUseCase(heroRepo)

    @Singleton
    @Provides
    fun provideHeroRepository(heroApi: HeroApi) = HeroRepo(heroApi)
}