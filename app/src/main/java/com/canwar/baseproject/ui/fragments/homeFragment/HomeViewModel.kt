package com.canwar.baseproject.ui.fragments.homeFragment

import com.canwar.baseproject.repository.Repository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class HomeViewModel @Inject constructor(
    private val repository: Repository
) {



}