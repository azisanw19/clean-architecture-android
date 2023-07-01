package com.canwar.baseproject.remote.api

import com.canwar.baseproject.model.responseModel.Game
import com.canwar.baseproject.model.responseModel.PagingGame
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    @GET("games")
    fun getGames(
        @Query("search")
        search: String?,

        @Query("page")
        page: Int,

        @Query("page_size")
        pageSize: Int
    ) : Single<PagingGame>

    @GET("games/{id_game}")
    fun detailGame(
        @Path("id_game") id: Int,
    ) : Observable<Game>

}