package com.canwar.baseproject.model.responseModel

data class PagingGame(
    val results: List<Game>,
    val next: String,
    val previous: String
) {



}