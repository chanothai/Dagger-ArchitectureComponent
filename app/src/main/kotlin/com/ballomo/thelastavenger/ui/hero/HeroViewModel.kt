package com.ballomo.thelastavenger.ui.hero

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ballomo.shared.domain.Result
import com.ballomo.thelastavenger.domain.LoadHeroUseCase
import com.ballomo.shared.util.map
import javax.inject.Inject

class HeroViewModel @Inject constructor(
    private val loadHeroUseCase: LoadHeroUseCase
) : ViewModel() {
    val postHeroListAdapter by lazy { PostHeroListAdapter() }
    val loadingVisibility by lazy { MutableLiveData<Int>() }

    val loadHeroResult: LiveData<Any> by lazy {
        loadHeroUseCase.observe().map {
            when(it) {
                is Result.Success -> {return@map it.data}
                is Result.Error -> {return@map it.exception}
                else -> {
                    return@map it == Result.Loading
                }
            }
        }
    }

    fun loadHeroInformation() {
        loadHeroUseCase.executeLoadAll(Unit)
    }
}