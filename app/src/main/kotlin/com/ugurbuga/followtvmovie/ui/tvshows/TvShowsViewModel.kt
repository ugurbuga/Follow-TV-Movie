package com.ugurbuga.followtvmovie.ui.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ugurbuga.followtvmovie.base.FTMBaseViewModel
import com.ugurbuga.followtvmovie.base.adapter.ListAdapterItem
import com.ugurbuga.followtvmovie.domain.favorite.GetFavoritesUseCase
import com.ugurbuga.followtvmovie.extensions.doOnSuccess
import com.ugurbuga.followtvmovie.ui.discover.DiscoverType
import com.ugurbuga.followtvmovie.ui.movie.FavoriteViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
class TvShowsViewModel @Inject constructor(
    private val getFavoriteUseCase: GetFavoritesUseCase,
) : FTMBaseViewModel() {

    private val _favoriteViewState = MutableLiveData<FavoriteViewState>()
    val favoriteViewState: LiveData<FavoriteViewState>
        get() = _favoriteViewState

    init {
        getFavoriteUseCase(GetFavoritesUseCase.GetFavoriteParams(DiscoverType.TV_SHOW)).doOnSuccess {
                _favoriteViewState.value = FavoriteViewState(it as ArrayList<ListAdapterItem>)
            }.launchIn(viewModelScope)
    }
}