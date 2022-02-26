package com.ugurbuga.followtvmovie.ui.tvshows

import androidx.lifecycle.viewModelScope
import com.ugurbuga.followtvmovie.base.FTMBaseViewModel
import com.ugurbuga.followtvmovie.base.adapter.ListAdapterItem
import com.ugurbuga.followtvmovie.domain.favorite.GetFavoritesUseCase
import com.ugurbuga.followtvmovie.extensions.doOnSuccess
import com.ugurbuga.followtvmovie.ui.discover.MediaType
import com.ugurbuga.followtvmovie.ui.movie.FavoriteViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn

@HiltViewModel
class TvShowsViewModel @Inject constructor(
    getFavoriteUseCase: GetFavoritesUseCase,
) : FTMBaseViewModel() {

    private val _favoriteViewState = MutableStateFlow(FavoriteViewState())
    val favoriteViewState: StateFlow<FavoriteViewState>
        get() = _favoriteViewState

    init {
        getFavoriteUseCase(GetFavoritesUseCase.GetFavoriteParams(MediaType.TV))
            .doOnSuccess {
                _favoriteViewState.value = FavoriteViewState(it as ArrayList<ListAdapterItem>)
            }.launchIn(viewModelScope)
    }
}