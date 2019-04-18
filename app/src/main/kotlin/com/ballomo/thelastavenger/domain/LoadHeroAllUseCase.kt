package com.ballomo.thelastavenger.domain

import com.ballomo.shared.data.HeroAdapter
import com.ballomo.shared.domain.MediatorUseCase
import com.ballomo.shared.domain.Result
import com.ballomo.thelastavenger.ui.hero.model.ListHeroInformation
import com.ballomo.thelastavenger.ui.hero.model.LoadHeroInformation

class LoadHeroUseCase (
    private val heroRepo: HeroAdapter
) : MediatorUseCase<Any, ListHeroInformation>() {

    override fun execute(parameters: Any){
        val heroSessionObservable = heroRepo.getAll()

        result.removeSource(heroSessionObservable)
        result.addSource(heroSessionObservable){
            when(it) {
            is Result.Success -> {
                val information = arrayListOf<LoadHeroInformation>()
                it.data.results.forEach {result->
                    information.add(
                        LoadHeroInformation(
                            result.name ?: "",
                            result.image ?: ""
                        )
                    )
                }

                result.postValue(ListHeroInformation(information))
            }
            is Result.Error -> {
                result.postValue(null) }
        }}
    }
}