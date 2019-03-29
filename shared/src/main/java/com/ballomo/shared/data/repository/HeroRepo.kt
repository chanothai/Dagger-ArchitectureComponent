package com.ballomo.shared.data.repository

import android.annotation.SuppressLint
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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HeroRepo @Inject constructor(
    private val heroApi: HeroApi
) : HeroAdapter {

    @SuppressLint("CheckResult")
    override fun getAll(): LiveData<Result<HeroEntity>> {
        val sessionResult = MediatorLiveData<Result<HeroEntity>>()

        heroApi.getHeros()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {sessionResult.postValue(Result.Success(it))},
                onError = {sessionResult.postValue(Result.Error(it))}
            )

        return sessionResult
}

    override fun get() {
        //Not yet implement
    }

    override fun getByPage(pageSize: Int): LiveData<Listing<Results>> {
        val sessionPaging = MediatorLiveData<Listing<Results>>()
        val sourceFactory = HeroDataSourceFactory(heroApi)

        val livePageList = sourceFactory.toLiveData(pageSize)

        Listing(
            pagedList = livePageList,
            networkState = Transformations.switchMap(sourceFactory.sourceLiveData) {
                it.networkState
            },
            refreshState = Transformations.switchMap(sourceFactory.sourceLiveData) {
                it.initialLoad
            },
            refresh = {sourceFactory.sourceLiveData.value?.invalidate()},
            retry = {sourceFactory.sourceLiveData.value?.retryAllFailed()}
        ).also {listing->
            sessionPaging.postValue(listing)
        }

        return sessionPaging
    }
}