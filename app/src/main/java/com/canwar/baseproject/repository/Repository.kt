package com.canwar.baseproject.repository

import com.canwar.baseproject.dataSource.ApiDataSource
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Repository  @Inject constructor(
    private val apiDataSource: ApiDataSource
) {



}