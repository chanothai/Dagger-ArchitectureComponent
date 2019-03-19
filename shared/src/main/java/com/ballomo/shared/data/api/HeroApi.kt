package com.ballomo.shared.data.api

import com.ballomo.shared.domain.hero.entity.HeroEntity
import io.reactivex.Observable
import retrofit2.http.GET

interface HeroApi {
    @GET("api/character")
    fun getHeros(): Observable<HeroEntity>
}