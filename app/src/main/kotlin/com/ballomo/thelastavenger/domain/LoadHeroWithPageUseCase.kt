package com.ballomo.thelastavenger.domain

import com.ballomo.shared.data.HeroAdapter
import com.ballomo.shared.data.Listing
import com.ballomo.shared.data.entity.hero.Results
import com.ballomo.shared.domain.MediatorUseCase
import com.ballomo.shared.domain.Result
import javax.inject.Inject

class LoadHeroWithPageUseCase(
    private val heroRepo: HeroAdapter
): MediatorUseCase<InputLoadHero, Listing<Results>>() {

    override fun execute(parameters: InputLoadHero) {
        val heroPagingLiveData = heroRepo.getByPage(parameters.pageSize)
        with(result) {
            this.removeSource(heroPagingLiveData)
            this.addSource(heroPagingLiveData) {
                this.postValue(it)
            }
        }
    }
}

data class InputLoadHero(
    val pageSize: Int
)