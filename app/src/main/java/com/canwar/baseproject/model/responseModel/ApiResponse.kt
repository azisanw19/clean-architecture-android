package com.canwar.baseproject.model.responseModel

data class ApiResponse<T>(
    val code: String,
    val message: String,
    val data: T
) {

    /* Manipulation Data */


}