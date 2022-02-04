package com.ugurbuga.followtvmovie.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.ugurbuga.followtvmovie.base.FTMBaseViewModel
import com.ugurbuga.followtvmovie.domain.poster.FavoriteUseCase
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import com.ugurbuga.followtvmovie.ui.discover.popularlist.PopularListType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val favoriteUseCase: FavoriteUseCase,
) : FTMBaseViewModel() {

    val posterList: LiveData<MutableList<PosterItemUIModel>>
        get() = favoriteUseCase(FavoriteUseCase.FavoriteParams(PopularListType.MOVIE)).asLiveData()
}