package com.ballomo.thelastavenger.ui.hero.api

import com.ballomo.thelastavenger.ui.hero.model.HeroApiResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface HeroApi {

    @GET("api/character")
    fun getHeros(): Observable<HeroApiResponse>
}