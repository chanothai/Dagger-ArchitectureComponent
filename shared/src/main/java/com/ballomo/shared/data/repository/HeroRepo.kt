package com.ballomo.shared.data.repository

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import androidx.paging.toLiveData
import com.ballomo.shared.data.HeroAdapter
import com.ballomo.shared.data.Listing
import com.ballomo.shared.data.api.HeroApi
import com.ballomo.shared.data.entity.HeroEntity
import com.ballomo.shared.data.entity.hero.Results
import com.ballomo.shared.data.source.HeroDataSourceFactory
import com.ballomo.shared.domain.Result
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class HeroRepo @Inject constructor(
    private val heroApi: HeroApi
) : HeroAdapter {

    private val sessionResult = MediatorLiveData<Result<HeroEntity>>()

    override fun getAll(): LiveData<Result<HeroEntity>> {
        heroApi.getHeros()
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = {sessionResult.postValue(Result.Success(it))},
                onError = {sessionResult.postValue(Result.Error(it))}
            ).isDisposed

    return sessionResult
}

    override fun get() {
        //Not yet implement
    }

    @MainThread
    override fun getByPage(pageSize: Int): Listing<Results> {
        val sourceFactory = HeroDataSourceFactory(heroApi)

        val livePageList = sourceFactory.toLiveData(pageSize)

        return Listing(
            pagedList = livePageList,
            networkState = Transformations.switchMap(sourceFactory.sourceLiveData) {
                it.networkState
            },
            refreshState = Transformations.switchMap(sourceFactory.sourceLiveData) {
                it.initialLoad
            },
            refresh = {sourceFactory.sourceLiveData.value?.invalidate()},
            retry = {sourceFactory.sourceLiveData.value?.retryAllFailed()}
        )
    }
}