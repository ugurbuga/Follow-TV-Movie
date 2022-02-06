package com.ugurbuga.followtvmovie.ui.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ugurbuga.followtvmovie.base.FTMBaseViewModel
import com.ugurbuga.followtvmovie.domain.poster.GetFavoriteUseCase
import com.ugurbuga.followtvmovie.ui.discover.DiscoverType
import com.ugurbuga.followtvmovie.ui.movie.FavoriteViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowsViewModel @Inject constructor(
    private val getFavoriteUseCase: GetFavoriteUseCase,
) : FTMBaseViewModel() {

    private val _favoriteViewState = MutableLiveData<FavoriteViewState>()
    val favoriteViewState: LiveData<FavoriteViewState>
        get() = _favoriteViewState

    init {
        viewModelScope.launch {
            getFavoriteUseCase(GetFavoriteUseCase.GetFavoriteParams(DiscoverType.MOVIE)).collect {
                //_favoriteViewState.value = FavoriteViewState(it)
            }
        }
    }
}