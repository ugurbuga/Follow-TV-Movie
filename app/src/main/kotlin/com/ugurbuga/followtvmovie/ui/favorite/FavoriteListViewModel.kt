package com.ugurbuga.followtvmovie.ui.favorite

import androidx.lifecycle.viewModelScope
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseViewModel
import com.ugurbuga.followtvmovie.core.adapter.ListAdapterItem
import com.ugurbuga.followtvmovie.core.common.Util
import com.ugurbuga.followtvmovie.core.extensions.doOnSuccess
import com.ugurbuga.followtvmovie.domain.favorite.usecase.GetFavoritesUseCase
import com.ugurbuga.followtvmovie.domain.poster.model.EmptyUIModel
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
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

    private var mainList: MutableList<PosterItemUIModel> = arrayListOf()
    private val _favoriteViewState = MutableStateFlow(FavoriteViewState())
    val favoriteViewState: StateFlow<FavoriteViewState>
        get() = _favoriteViewState

    var searchQuery: String = Util.EMPTY_STRING
    private var mediaType: String = Util.EMPTY_STRING
    private var favoriteListType: String = Util.EMPTY_STRING

    fun setFavoriteListType(mediaType: String, favoriteListType: String) {
        this.mediaType = mediaType
        this.favoriteListType = favoriteListType
        val isWatched = favoriteListType == FavoriteListType.WATCHED_LIST
        getFavoriteUseCase(
            GetFavoritesUseCase.GetFavoriteParams(
                mediaType, isWatched
            )
        ).doOnSuccess {
            mainList = it
            setFilterList()
        }.launchIn(viewModelScope)
    }

    private fun getEmptyMessageId(mediaType: String, favoriteListType: String): Any {
        return if (mediaType == MediaType.MOVIE && favoriteListType == FavoriteListType.WATCHED_LIST) {
            R.string.empty_watched_movie_list

        } else if (mediaType == MediaType.MOVIE && favoriteListType == FavoriteListType.WATCH_LATER_LIST) {
            R.string.empty_watch_later_movie_list

        } else if (mediaType == MediaType.TV && favoriteListType == FavoriteListType.WATCHED_LIST) {
            R.string.empty_finished_tv_show_list

        } else if (mediaType == MediaType.TV && favoriteListType == FavoriteListType.WATCH_LATER_LIST) {
            R.string.empty_continued_tv_show_list

        } else {
            Util.EMPTY_STRING
        }

    }

    fun setQuery(query: String) {
        searchQuery = query
        setFilterList()
    }

    @Suppress("UNCHECKED_CAST")
    private fun setFilterList() {
        if (searchQuery.isNotEmpty()) {
            val filteredList = mainList.filter {
                it.name.contains(
                    searchQuery,
                    ignoreCase = true
                )
            }.toMutableList()
            setList(filteredList as ArrayList<ListAdapterItem>)
        } else {
            if (mainList.isEmpty()) {
                setEmptyState()
            } else {
                setList(mainList as ArrayList<ListAdapterItem>)

            }
        }
    }

    private fun setList(list: ArrayList<ListAdapterItem>) {
        if (list.isEmpty()) {
            setEmptyState()
        } else {
            _favoriteViewState.value = FavoriteViewState(list)
        }
    }

    private fun setEmptyState() {
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
    }
}