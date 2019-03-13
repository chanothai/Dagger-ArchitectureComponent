package com.ballomo.thelastavenger.ui.hero

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ballomo.shared.result.Event
import com.ballomo.thelastavenger.ui.hero.api.HeroApi
import com.ballomo.thelastavenger.ui.hero.model.HeroApiResponse
import com.ballomo.thelastavenger.util.UserPreference
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HeroViewModel @Inject constructor(
    private val userPreference: UserPreference,
    private val heroApi: HeroApi) : ViewModel() {

    private val subscription by lazy { CompositeDisposable() }

    private val mMessage = MutableLiveData<Event<String>>()
    val messageLiveData: LiveData<Event<String>>
    get() = mMessage

    fun loadHeroInformation() {
        heroApi.getHeros()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{onPostStart()}
            .doOnTerminate{onPostFinish()}
            .subscribeBy (
                onNext = {onPostSuccess(it)},
                onError = {onPostError()}
            )
            .addTo(subscription)
    }

    private fun onPostStart() {

    }

    private fun onPostFinish() {

    }

    private fun onPostSuccess(apiResponse: HeroApiResponse) {
        mMessage.value = Event(apiResponse.results[0].name ?: "No Name")
    }

    private fun onPostError() {
        mMessage.value = Event(userPreference.getUserName() ?: "User was null")
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}