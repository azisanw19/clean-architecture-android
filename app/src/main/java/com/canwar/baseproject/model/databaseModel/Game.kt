package com.canwar.baseproject.model.databaseModel

import com.google.gson.annotations.SerializedName

data class Game(
    val id: Int,
    val title: String,
    val released: String,
    val backgroundImage: String,
    val rating: Double,
    val description: String
) {

    

}
