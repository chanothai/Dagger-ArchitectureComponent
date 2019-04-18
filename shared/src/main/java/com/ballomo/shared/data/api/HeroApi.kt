package com.ballomo.shared.data.api

import com.ballomo.shared.data.entity.HeroEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface HeroApi {
    @GET("api/character")
    fun getHeros(): Single<HeroEntity>

    @GET("api/character/")
    fun getHeroByPage(@Query("page") page: Int): Single<HeroEntity>
}