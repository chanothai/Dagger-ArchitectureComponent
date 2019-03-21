package com.ballomo.shared

import androidx.paging.DataSource
import com.ballomo.shared.data.PageHeroDataSource
import com.ballomo.shared.data.api.HeroApi
import com.ballomo.shared.domain.hero.entity.HeroEntity
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

class HeroDataSourceFactory(
    private val heroAPI: HeroApi,
    private val disposable: CompositeDisposable,
    private val page: String
): DataSource.Factory<String, HeroEntity>() {

    lateinit var sourceObservable: Observable<PageHeroDataSource>

    override fun create(): DataSource<String, HeroEntity> {
        val source = PageHeroDataSource(heroAPI, page)
        sourceObservable = Observable.just(source)

        return source
    }
}