package com.ballomo.thelastavenger.di.component

import com.ballomo.shared.data.HeroAdapter
import com.ballomo.shared.data.api.HeroApi
import com.ballomo.shared.data.repository.HeroRepo
import com.ballomo.thelastavenger.common.AwesomeApplication
import com.ballomo.thelastavenger.di.module.*
import com.ballomo.thelastavenger.domain.LoadHeroUseCase
import com.ballomo.thelastavenger.domain.LoadHeroWithPageUseCase
import com.ballomo.thelastavenger.network.OKHttpClientFactory
import com.ballomo.thelastavenger.network.RetrofitFactory
import com.ballomo.thelastavenger.ui.hero.all.HeroViewModel
import com.ballomo.thelastavenger.ui.hero.paging.HeroPagingViewModel
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        AppModule::class,
        ViewModelModule::class,
        NetWorkModule::class,
        HeroUseCaseModule::class
    ]
)

interface AppComponent : AndroidInjector<AwesomeApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<AwesomeApplication>()
}

class Component {
    companion object {
        val networkModule = module {
            single { OKHttpClientFactory() }
            single { RetrofitFactory(get()).retrofitBuilder().create(HeroApi::class.java) }
        }

        val appModule = module {
            single<HeroAdapter> { HeroRepo(get()) }
            single { LoadHeroUseCase(get()) }
            viewModel { HeroViewModel(get()) }
        }

        val heroPagingModule = module {
            single { LoadHeroWithPageUseCase(get()) }
            viewModel { HeroPagingViewModel(get()) }
        }
    }
}