package com.ballomo.shared.data.source

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.ballomo.shared.data.NetworkState
import com.ballomo.shared.data.api.HeroApi
import com.ballomo.shared.data.entity.hero.Results
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class PageHeroDataSource(
    private val heroAPI: HeroApi): PageKeyedDataSource<Int, Results>() {

    //init page
    var page = 1

    // keep a function reference for the retry event
    private var retry: (() -> Any)? = null

    val networkState = MutableLiveData<NetworkState>()
    val initialLoad = MutableLiveData<NetworkState>()

    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.let {
            val executor:Executor = Executors.newFixedThreadPool(5)
             executor.execute {
                it.invoke()
            }
        }
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Results>) {
        heroAPI.getHeroByPage(page)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                networkState.postValue(NetworkState.LOADING)
                initialLoad.postValue(NetworkState.LOADING) }
            .doFinally {
                networkState.postValue(NetworkState.LOADED)
                initialLoad.postValue(NetworkState.LOADED) }
            .subscribeBy(
                onSuccess = {
                    callback.onResult(it.results,0, it.info.pages ?: 1,1 , 2)
                },
                onError = {
                    retry = {
                        loadInitial(params, callback)
                    }

                    val error = NetworkState.error(it.message ?: "unknown error")
                    networkState.postValue(error)
                    initialLoad.postValue(error)
                }
            ).isDisposed
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Results>) {
        heroAPI.getHeroByPage(params.key)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { networkState.postValue(NetworkState.LOADING) }
            .doFinally { networkState.postValue(NetworkState.LOADED) }
            .subscribeBy(
                onSuccess = {
                    retry = null
                    callback.onResult(it.results, params.key.plus(1))
                },
                onError = {
                    retry = {
                        loadAfter(params, callback)
                    }

                    networkState.postValue(
                        NetworkState.error(
                            it.message ?: "unknown error"
                        )
                    )
                }
            ).isDisposed
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Results>) {
        //WTF
    }
}