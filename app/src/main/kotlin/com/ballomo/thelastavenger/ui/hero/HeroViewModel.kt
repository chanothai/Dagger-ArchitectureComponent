package com.ballomo.thelastavenger.ui.hero

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.ballomo.shared.domain.Result
import com.ballomo.shared.domain.hero.ListHeroInformation
import com.ballomo.shared.domain.hero.LoadHeroUseCase
import com.ballomo.shared.domain.succeeded
import com.ballomo.shared.result.Event
import com.ballomo.shared.util.map
import com.ballomo.thelastavenger.ui.hero.model.HeroInformationModel
import io.reactivex.disposables.CompositeDisposable
import java.lang.Exception
import java.lang.NullPointerException
import javax.inject.Inject

class HeroViewModel @Inject constructor(
    private val loadHeroUseCase: LoadHeroUseCase
) : ViewModel() {

    val postHeroListAdapter by lazy { PostHeroListAdapter() }
    val loadingVisibility by lazy { MutableLiveData<Int>() }

    var heroInformation: LiveData<Any>? = null

    private val loadHeroResult: LiveData<Result<ListHeroInformation>> by lazy {
        loadHeroUseCase.observe()
    }

    fun loadHeroInformation() {
        loadHeroUseCase.execute(Unit)
        heroInformation = loadHeroResult.map {
            when(it) {
                is Result.Success -> {it.data}
                is Result.Error -> {it.exception}
                else -> {
                    it == Result.Loading
                }
            }
        }
    }
}