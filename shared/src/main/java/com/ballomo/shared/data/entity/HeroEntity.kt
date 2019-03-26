package com.ballomo.shared.data.entity

import com.ballomo.shared.data.entity.hero.Info
import com.ballomo.shared.data.entity.hero.Results
import com.google.gson.annotations.SerializedName

data class HeroEntity(
    @SerializedName("info")
    var info: Info = Info(),
    @SerializedName("results")
    var results: List<Results> = listOf()
)