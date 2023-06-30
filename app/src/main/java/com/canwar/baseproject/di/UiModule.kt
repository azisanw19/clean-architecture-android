package com.canwar.baseproject.di

import com.canwar.baseproject.ui.adapters.GameAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object UiModule {

    @Provides
    fun provideGameAdapter() = GameAdapter()

}
