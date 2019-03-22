package com.ballomo.shared.domain.hero

import com.ballomo.shared.data.repository.HeroAdapter
import com.ballomo.shared.domain.MediatorUseCase
import com.ballomo.shared.domain.Result
import javax.inject.Inject

class LoadHeroUseCase @Inject constructor(
    private val heroRepo: HeroAdapter
) : MediatorUseCase<Any, ListHeroInformation>() {

    override fun execute(parameters: Any){
        result.postValue(Result.Loading)

        val heroSessionObservable = heroRepo.getAll()

        result.removeSource(heroSessionObservable)
        result.addSource(heroSessionObservable) {
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

                    result.postValue(Result.Success(ListHeroInformation(information)))
                }
                is Result.Error -> { result.postValue(it) }
            }
        }
    }
}

data class LoadHeroInformation(
    var name: String,
    var image: String
)

data class ListHeroInformation(
    var heroInformation: List<LoadHeroInformation>
)