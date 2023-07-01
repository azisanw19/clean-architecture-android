package com.canwar.baseproject.dataSource

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.canwar.baseproject.model.responseModel.Game
import com.canwar.baseproject.model.responseModel.PagingGame
import com.canwar.baseproject.remote.api.ApiServices
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GamesPagingSource(
    private val apiServices: ApiServices,
    private val query: String?
) : RxPagingSource<Int, Game>() {

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Game>> {
        val page = params.key ?: INITIAL_PAGE_INDEX

        return apiServices
            .getGames(
                search = query,
                page = page,
                pageSize = params.loadSize
            )
            .subscribeOn(Schedulers.io())
            .map {
                toLoadResult(it, page)
            }
            .onErrorReturn {
                LoadResult.Error(it)
            }

    }

    private fun toLoadResult(pagingGame: PagingGame, page: Int) : LoadResult<Int, Game> = LoadResult.Page(
        data = pagingGame.results,
        prevKey = if (page == 1) null else page - 1,
        nextKey = if (pagingGame.results.isEmpty()) null else page + 1
    )

    override fun getRefreshKey(state: PagingState<Int, Game>): Int? = state.anchorPosition?.let { anchorPosition ->
        val anchorPage = state.closestPageToPosition(anchorPosition)
        anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
    }


}
