package com.ugurbuga.followtvmovie.ui.movie

import androidx.lifecycle.viewModelScope
import com.ugurbuga.followtvmovie.base.FTMBaseViewModel
import com.ugurbuga.followtvmovie.base.adapter.ListAdapterItem
import com.ugurbuga.followtvmovie.domain.favorite.GetFavoritesUseCase
import com.ugurbuga.followtvmovie.domain.poster.model.EmptyUIModel
import com.ugurbuga.followtvmovie.extensions.doOnSuccess
import com.ugurbuga.followtvmovie.ui.discover.DiscoverType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getFavoriteUseCase: GetFavoritesUseCase,
) : FTMBaseViewModel() {

    private val _favoriteViewState = MutableStateFlow(FavoriteViewState())
    val favoriteViewState: StateFlow<FavoriteViewState>
        get() = _favoriteViewState

    init {
        getFavorites()
    }

    @Suppress("UNCHECKED_CAST")
    private fun getFavorites() {
        getFavoriteUseCase(GetFavoritesUseCase.GetFavoriteParams(DiscoverType.MOVIE)).doOnSuccess {
            if (it.isEmpty()) {
                _favoriteViewState.value = FavoriteViewState(arrayListOf(EmptyUIModel()))

            } else {
                _favoriteViewState.value = FavoriteViewState(it as ArrayList<ListAdapterItem>)
            }

        }.launchIn(viewModelScope)
    }
}