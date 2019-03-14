package com.ballomo.shared.domain.api

import com.ballomo.shared.domain.entity.HeroEntity
import io.reactivex.Observable
import retrofit2.http.GET

interface HeroApi {
    @GET("api/character")
    fun getHeros(): Observable<HeroEntity>
}