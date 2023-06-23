package com.canwar.baseproject.model.databaseModel

data class PagingGame(
    val results: List<Game>,
    val next: String,
    val previous: String
) {



}