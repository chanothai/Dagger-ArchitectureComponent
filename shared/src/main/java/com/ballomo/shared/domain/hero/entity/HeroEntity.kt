package com.ballomo.shared.domain.hero.entity

import com.google.gson.annotations.SerializedName

data class HeroEntity(
    @SerializedName("info")
    var info: Info = Info(),
    @SerializedName("results")
    var results: List<Results> = listOf()
)

data class Info(
    @SerializedName("count")
    var count: Int? = null,
    @SerializedName("pages")
    var pages: Int? = null,
    @SerializedName("next")
    var next: String? = null,
    @SerializedName("prev")
    var prev: String? = null
)

data class Results(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("species")
    val species: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("gender")
    val gender: String? = null,
    @SerializedName("origin")
    val origin: Origin? = null,
    @SerializedName("location")
    val location: Location? = null,
    @SerializedName("image")
    val image: String? = null
)

data class Location(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("url")
    val url: String? = null
)

data class Origin(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("url")
    val url: String? = null
)

