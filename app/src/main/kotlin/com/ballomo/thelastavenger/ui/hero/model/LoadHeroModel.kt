package com.ballomo.thelastavenger.ui.hero.model

data class LoadHeroInformation(
    var name: String,
    var image: String
)

data class ListHeroInformation(
    var heroInformation: List<LoadHeroInformation>
)