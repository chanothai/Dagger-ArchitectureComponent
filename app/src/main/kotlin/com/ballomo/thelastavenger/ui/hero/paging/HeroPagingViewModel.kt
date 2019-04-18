package com.ballomo.thelastavenger.ui.hero.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.ballomo.shared.data.Listing
import com.ballomo.shared.data.entity.hero.Results
import com.ballomo.shared.util.map
import com.ballomo.thelastavenger.domain.InputLoadHero
import com.ballomo.thelastavenger.domain.LoadHeroWithPageUseCase
import java.lang.NullPointerException
import javax.inject.Inject

class HeroPagingViewModel@Inject constructor(
    private val loadHeroWithPageUseCase: LoadHeroWithPageUseCase
): ViewModel() {

    private val loadHeroResult: LiveData<Listing<Results>> by lazy {
        loadHeroWithPageUseCase.observe().map {
            return@map it
        }
    }

    val loadHeroPagedListResult = Transformations.switchMap(loadHeroResult) { it.pagedList }!!
    val networkState = Transformations.switchMap(loadHeroResult) { it.networkState }!!

    fun requestHeroPaging(parameters: InputLoadHero) {
        loadHeroWithPageUseCase.execute(parameters)
    }

    fun retryLoadHero() {
        loadHeroResult.value?.retry?.invoke()
    }

    fun refreshLoadHero() {
        loadHeroResult.value?.refresh?.invoke()
    }
}