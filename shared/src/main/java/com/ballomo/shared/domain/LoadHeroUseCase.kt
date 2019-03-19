package com.ballomo.shared.domain

import com.ballomo.shared.domain.repository.HeroAdapter
import io.reactivex.Observable
import javax.inject.Inject

class LoadHeroUseCase @Inject constructor(
    private val heroRepo: HeroAdapter
) : MediatorUseCase<Any, Observable<ListHeroInformation>>() {

    override fun execute(parameters: Any): Observable<ListHeroInformation> {
        return heroRepo.getAll()
            .map {
                val information = arrayListOf<LoadHeroInformation>()
                it.results.forEach { result ->
                    information.add(
                        LoadHeroInformation(
                            result.name ?: "",
                            result.image ?: ""
                        )
                    )
                }
                ListHeroInformation(information)
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

