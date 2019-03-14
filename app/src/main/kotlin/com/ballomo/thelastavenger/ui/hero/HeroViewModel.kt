package com.ballomo.thelastavenger.ui.hero

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ballomo.shared.domain.LoadHeroUseCase
import com.ballomo.shared.domain.api.HeroApi
import com.ballomo.shared.result.Event
import com.ballomo.shared.domain.entity.HeroEntity
import com.ballomo.thelastavenger.util.UserPreference
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HeroViewModel @Inject constructor(
    private val userPreference: UserPreference,
    private val loadHeroUseCase: LoadHeroUseCase
) : ViewModel() {

    private val disposable by lazy { CompositeDisposable() }

    private val mMessage = MutableLiveData<Event<String>>()
    val messageLiveData: LiveData<Event<String>>
    get() = mMessage

    fun loadHeroInformation() {
        loadHeroUseCase.executeResponse(Unit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                val information = arrayListOf<HeroInformationModel>()
                it.results.forEach {result->
                    information.add(HeroInformationModel(result.name ?: ""))
                }
                information
            }
            .subscribeBy (
                onNext = {
                    onPostSuccess(it)
                },
                onError = {onPostError(it)}
            ).addTo(disposable)
    }

    private fun onPostStart() {

    }

    private fun onPostFinish() {

    }

    private fun onPostSuccess(information: List<HeroInformationModel>) {
        mMessage.value = Event(information[0].name)
    }

    private fun onPostError(exception: Throwable) {
        mMessage.value = Event(exception.message ?: "null")
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}

data class HeroInformationModel(var name: String)