package com.canwar.baseproject.dataSource

import com.canwar.baseproject.remote.api.ApiServices
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class ApiDataSource @Inject constructor(
    private val apiServices: ApiServices
) {



}