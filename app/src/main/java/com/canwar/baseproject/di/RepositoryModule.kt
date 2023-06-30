package com.canwar.baseproject.di

import com.canwar.baseproject.remote.api.ApiServices
import com.canwar.baseproject.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(apiServices: ApiServices) = Repository(apiServices)

}