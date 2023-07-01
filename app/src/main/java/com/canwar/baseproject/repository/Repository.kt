package com.canwar.baseproject.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.rxjava3.flowable
import com.canwar.baseproject.dataSource.GamesPagingSource
import com.canwar.baseproject.remote.api.ApiServices
import com.canwar.baseproject.utils.NETWORK_PAGE_SIZE

class Repository(private val apiServices: ApiServices) {

    fun getGameSearchFromRemote(query: String) = Pager(
        config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { GamesPagingSource(apiServices, query) }
    ).flowable

}