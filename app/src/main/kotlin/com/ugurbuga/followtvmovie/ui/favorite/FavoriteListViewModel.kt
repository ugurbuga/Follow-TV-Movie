package com.ugurbuga.followtvmovie.ui.favorite

import androidx.lifecycle.viewModelScope
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseViewModel
import com.ugurbuga.followtvmovie.base.adapter.ListAdapterItem
import com.ugurbuga.followtvmovie.common.Util
import com.ugurbuga.followtvmovie.domain.favorite.GetFavoritesUseCase
import com.ugurbuga.followtvmovie.domain.poster.model.EmptyUIModel
import com.ugurbuga.followtvmovie.extensions.doOnSuccess
import com.ugurbuga.followtvmovie.ui.discover.MediaType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn

@HiltViewModel
class FavoriteListViewModel @Inject constructor(
    private val getFavoriteUseCase: GetFavoritesUseCase,
) : FTMBaseViewModel() {

    private val _favoriteViewState = MutableStateFlow(FavoriteViewState())
    val favoriteViewState: StateFlow<FavoriteViewState>
        get() = _favoriteViewState

    @Suppress("UNCHECKED_CAST")
    fun setFavoriteListType(mediaType: String, favoriteListType: String) {
        val isWatched = favoriteListType == FavoriteListType.WATCHED_LIST
        getFavoriteUseCase(
            GetFavoritesUseCase.GetFavoriteParams(
                mediaType, isWatched
            )
        ).doOnSuccess {
            if (it.isEmpty()) {
                _favoriteViewState.value =
                    FavoriteViewState(
                        arrayListOf(
                            EmptyUIModel(
                                message = getEmptyMessageId(
                                    mediaType,
                                    favoriteListType
                                )
                            )
                        )
                    )

            } else {
                _favoriteViewState.value = FavoriteViewState(it as ArrayList<ListAdapterItem>)
            }

        }.launchIn(viewModelScope)
    }

    private fun getEmptyMessageId(mediaType: String, favoriteListType: String): Any {
        return if (mediaType == MediaType.MOVIE && favoriteListType == FavoriteListType.WATCHED_LIST) {
            R.string.empty_watched_movie_list

        } else if (mediaType == MediaType.MOVIE && favoriteListType == FavoriteListType.WATCH_LATER_LIST) {
            R.string.empty_watch_later_movie_list

        } else if (mediaType == MediaType.TV && favoriteListType == FavoriteListType.WATCHED_LIST) {
            R.string.empty_watched_tv_show_list

        } else if (mediaType == MediaType.TV && favoriteListType == FavoriteListType.WATCH_LATER_LIST) {
            R.string.empty_watch_later_tv_show_list

        } else {
            Util.EMPTY_STRING
        }

    }
}