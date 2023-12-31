package com.canwar.baseproject.model.responseModel

import com.google.gson.annotations.SerializedName

data class Game(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("released")
    val released: String,

    @SerializedName("background_image")
    val backgroundImage: String,

    @SerializedName("rating")
    val rating: Double,

    @SerializedName("description")
    val description: String? = null
)
