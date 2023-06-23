package com.canwar.baseproject.model.databaseModel

import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(
    val code: String,
    val message: String,
    val data: T
) {

    /* Manipulation Data */


}