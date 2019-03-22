package com.ballomo.shared.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.ballomo.shared.data.api.HeroApi
import com.ballomo.shared.domain.Result
import com.ballomo.shared.domain.hero.entity.HeroEntity
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HeroRepo @Inject constructor(
    private val heroApi: HeroApi
) : HeroAdapter {

    private val sessionResult = MediatorLiveData<Result<HeroEntity>>()

    override fun getAll(): LiveData<Result<HeroEntity>> {
        heroApi.getHeros()
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onNext = {sessionResult.postValue(Result.Success(it))},
                onError = {sessionResult.postValue(Result.Error(it))}
            ).isDisposed

    return sessionResult
}

    override fun get() {

    }

//    override fun getAllByPage(page: String): Observable<HeroDataSourceFactory> {
//        val source = HeroDataSourceFactory(heroApi, disposable, page)
//        return Observable.just(source)
//    }
}