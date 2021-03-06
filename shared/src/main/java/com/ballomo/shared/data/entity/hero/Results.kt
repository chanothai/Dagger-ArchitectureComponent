package com.ballomo.shared.data.entity.hero

import com.google.gson.annotations.SerializedName

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