package com.ballomo.shared.data.api

import com.ballomo.shared.data.entity.HeroEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface HeroApi {
    @GET("api/character")
    fun getHeros(): Observable<HeroEntity>

    @GET("api/character/")
    fun getHeroByPage(@Query("page") page: Int): Observable<HeroEntity>
}