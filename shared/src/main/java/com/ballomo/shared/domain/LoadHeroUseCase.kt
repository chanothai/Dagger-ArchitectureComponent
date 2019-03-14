package com.ballomo.shared.domain

import com.ballomo.shared.domain.repository.HeroAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class LoadHeroUseCase @Inject constructor(
    private val heroRepo: HeroAdapter
): MediatorUseCase<Any, Observable<ArrayList<HeroInformationModel>>>() {

    override fun execute(parameters: Any): Observable<ArrayList<HeroInformationModel>> {
        return heroRepo.getAll()
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                val information = arrayListOf<HeroInformationModel>()
                it.results.forEach {result->
                    information.add(HeroInformationModel(result.name ?: ""))
                }
                information
            }
    }
}

data class HeroInformationModel(var name: String)