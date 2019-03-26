package com.ballomo.thelastavenger.domain

import com.ballomo.shared.data.HeroAdapter
import com.ballomo.shared.data.Listing
import com.ballomo.shared.data.entity.hero.Results
import javax.inject.Inject

class LoadHeroWithPageUseCase@Inject constructor(
    private val heroRepo: HeroAdapter
) {
    fun execute(inputLoadHero: InputLoadHero): Listing<Results> = heroRepo.getByPage(inputLoadHero.pageSize)
}

data class InputLoadHero(
    val pageSize: Int
)