package com.ballomo.shared.domain

import com.ballomo.shared.domain.entity.HeroEntity
import com.ballomo.shared.domain.repository.HeroAdapter
import io.reactivex.Observable
import javax.inject.Inject

class LoadHeroUseCase @Inject constructor(
    private val heroRepo: HeroAdapter
): MediatorUseCase<Any, Observable<HeroEntity>>() {

    override fun execute(parameters: Any) {

    }

    override fun executeResponse(parameters: Any): Observable<HeroEntity> {
        result.postValue(Result.Loading)
        return heroRepo.getAll()
    }
}