package com.ugurbuga.followtvmovie.ui.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.ugurbuga.followtvmovie.base.FTMBaseViewModel
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import com.ugurbuga.followtvmovie.repository.favorites.FavoritesRepository
import com.ugurbuga.followtvmovie.ui.discover.popularlist.PopularListType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TvShowsViewModel @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
) : FTMBaseViewModel() {

    val posterList: LiveData<MutableList<PosterItemUIModel>>
        get() = favoritesRepository.getFavorites(
            PopularListType.TV_SHOW
        ).asLiveData()
}