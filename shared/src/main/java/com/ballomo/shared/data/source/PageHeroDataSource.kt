package com.ballomo.shared.data.source

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.ballomo.shared.data.NetworkState
import com.ballomo.shared.data.api.HeroApi
import com.ballomo.shared.data.entity.hero.Results
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executor
import java.util.concurrent.Executors

@SuppressLint("CheckResult")
class PageHeroDataSource(
    private val heroAPI: HeroApi): PageKeyedDataSource<Int, Results>() {

    //init page
    private var page = 1

    // keep a function reference for the retry event
    private var retry: (() -> Any)? = null

    val networkState = MutableLiveData<NetworkState>()
    val initialLoad = MutableLiveData<NetworkState>()

    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.let {
            Executors.newFixedThreadPool(5).also {excutor->
                excutor.execute {
                    it.invoke()
                }
            }
        }
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Results>) {
        heroAPI.getHeroByPage(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                networkState.postValue(NetworkState.LOADING)
                initialLoad.postValue(NetworkState.LOADING) }
            .subscribeBy(
                onSuccess = {
                    networkState.postValue(NetworkState.LOADED)
                    initialLoad.postValue(NetworkState.LOADED)
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
            )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Results>) {
        heroAPI.getHeroByPage(params.key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { networkState.postValue(NetworkState.LOADING) }
            .subscribeBy(
                onSuccess = {
                    networkState.postValue(NetworkState.LOADED)
                    retry = null
                    callback.onResult(it.results, params.key.plus(1))
                },
                onError = {
                    retry = {
                        loadAfter(params, callback)
                    }

                    networkState.postValue(NetworkState.error(it.message ?: "unknown error"))
                }
            )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Results>) {
        //WTF
    }
}