package com.ballomo.shared.domain.repository

import com.ballomo.shared.domain.api.HeroApi
import com.ballomo.shared.domain.entity.HeroEntity
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HeroRepo @Inject constructor(
    private val heroApi: HeroApi
) : HeroAdapter {

    override fun getAll(): Observable<HeroEntity> {
       return heroApi.getHeros()
    }

    override fun get() {

    }
}