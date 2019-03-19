package com.ballomo.thelastavenger.ui.hero

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ballomo.shared.domain.ListHeroInformation
import com.ballomo.shared.domain.LoadHeroUseCase
import com.ballomo.shared.result.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class HeroViewModel @Inject constructor(
    private val loadHeroUseCase: LoadHeroUseCase
) : ViewModel() {

    val postHeroListAdapter = PostHeroListAdapter()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    private val disposable by lazy { CompositeDisposable() }

    private val mMessage = MutableLiveData<Event<String>>()
    val messageLiveData: LiveData<Event<String>>
    get() = mMessage

    fun loadHeroInformation() {
        loadHeroUseCase.execute(Unit)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onPostStart() }
            .doOnTerminate { onPostFinish() }
            .subscribeBy (
                onNext = { onPostSuccess(it) },
                onError = {onPostError(it)}
            ).addTo(disposable)
    }

    private fun onPostStart() {
        loadingVisibility.value = View.VISIBLE
    }

    private fun onPostFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onPostSuccess(listInformation: ListHeroInformation) {
        postHeroListAdapter.updateListHeroInformation(listInformation)
    }

    private fun onPostError(exception: Throwable) {
        mMessage.value = Event(exception.message ?: "No exception")
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}