package com.ballomo.shared.data

import androidx.paging.PageKeyedDataSource
import com.ballomo.shared.data.api.HeroApi
import com.ballomo.shared.domain.hero.entity.HeroEntity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import com.ballomo.shared.domain.Result

class PageHeroDataSource(
    private val heroAPI: HeroApi,
    private val page: String
): PageKeyedDataSource<String, HeroEntity>() {
    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, HeroEntity>
    ) {

    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, HeroEntity>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, HeroEntity>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}