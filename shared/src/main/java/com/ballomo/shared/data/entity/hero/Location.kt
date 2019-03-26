package com.ballomo.shared.data.entity.hero

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("url")
    val url: String? = null
)