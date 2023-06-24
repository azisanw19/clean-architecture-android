package com.canwar.baseproject.remote.api

import com.canwar.baseproject.model.databaseModel.Game
import com.canwar.baseproject.model.databaseModel.PagingGame
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    @GET("games")
    suspend fun getGames(
        @Query("page") page: Int,
        @Query("page_size") count: Int = 20,
        @Query("search") search: String? = null,
        @Query("key") key: String = "10a79a5e331e40d48d9b7e470d0ff6c5"
    ) : Observable<PagingGame>

    @GET("games/{id_game}")
    suspend fun detailGame(
        @Path("id_game") id: Int,
        @Query("key") key: String = "10a79a5e331e40d48d9b7e470d0ff6c5"
    ) : Observable<Game>

}