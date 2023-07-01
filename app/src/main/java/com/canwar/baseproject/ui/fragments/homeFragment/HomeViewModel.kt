package com.canwar.baseproject.ui.fragments.homeFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.canwar.baseproject.model.responseModel.Game
import com.canwar.baseproject.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    init {
        getGameData("")
    }

    private val _pagingDataGame = MutableSharedFlow<PagingData<Game>>()
    val pagingDataGame = _pagingDataGame.asSharedFlow()

    private val _throwable = MutableSharedFlow<Throwable>()
    val throwable = _throwable.asSharedFlow()

    private var subscription: Disposable? = null

    fun getGameData(query: String) {
        subscription = repository.getGameSearchFromRemote(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    viewModelScope.launch { _pagingDataGame.emit(it) }
                },
                {
                    viewModelScope.launch { _throwable.emit(it) }
                }
            )
    }

    override fun onCleared() {
        subscription?.dispose()
        super.onCleared()
    }

}