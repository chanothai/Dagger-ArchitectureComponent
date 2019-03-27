package com.ballomo.thelastavenger.ui.hero.paging

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.ballomo.thelastavenger.domain.InputLoadHero
import com.ballomo.thelastavenger.domain.LoadHeroWithPageUseCase
import javax.inject.Inject

class HeroPagingViewModel@Inject constructor(
    private val loadHeroWithPageUseCase: LoadHeroWithPageUseCase
): ViewModel() {

    private val inputLoadHero = MutableLiveData<InputLoadHero>()
    private val repoResult = Transformations.map(inputLoadHero) {
        loadHeroWithPageUseCase.execute(it)
    }

    val loadHeroPagedListResult = Transformations.switchMap(repoResult) { it.pagedList }!!

    val networkState = Transformations.switchMap(repoResult) { it.networkState }!!

    fun requestHeroPaging(parameters: InputLoadHero): Boolean {
        if (inputLoadHero.value == parameters) {
            return false
        }

        inputLoadHero.value = parameters
        return true
    }

    fun retryLoadHero() {
        repoResult.value?.retry?.invoke()
    }

    fun refreshLoadHero() {
        repoResult.value?.refresh?.invoke()
    }
}