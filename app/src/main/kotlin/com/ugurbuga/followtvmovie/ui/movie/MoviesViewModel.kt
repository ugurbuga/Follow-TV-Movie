package com.ugurbuga.followtvmovie.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ugurbuga.followtvmovie.base.FTMBaseViewModel
import com.ugurbuga.followtvmovie.common.Resource
import com.ugurbuga.followtvmovie.domain.poster.GetFavoriteUseCase
import com.ugurbuga.followtvmovie.ui.discover.DiscoverType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getFavoriteUseCase: GetFavoriteUseCase,
) : FTMBaseViewModel() {

    private val _favoriteViewState = MutableLiveData<FavoriteViewState>()
    val favoriteViewState: LiveData<FavoriteViewState>
        get() = _favoriteViewState

    init {
        viewModelScope.launch {
            getFavoriteUseCase(GetFavoriteUseCase.GetFavoriteParams(DiscoverType.MOVIE)).collect {
                if (it is Resource.Success) {
                    _favoriteViewState.value = FavoriteViewState(it.data)
                }
            }
        }
    }
}

fun ViewModel.launchInVMScope(function: suspend () -> Unit) {
    viewModelScope.launch {
        function.invoke()
    }
}