package com.ballomo.shared.data.repository

import com.ballomo.shared.data.api.HeroApi
import com.ballomo.shared.domain.hero.entity.HeroEntity
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HeroRepo @Inject constructor(
    private val heroApi: HeroApi
) : HeroAdapter {

    override fun getAll(): Observable<HeroEntity> {
       return heroApi.getHeros().subscribeOn(Schedulers.io())
    }

    override fun get() {

    }
}