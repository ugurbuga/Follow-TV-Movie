package com.ugurbuga.followtvmovie.ui.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.ugurbuga.followtvmovie.base.FTMBaseViewModel
import com.ugurbuga.followtvmovie.base.adapter.ListAdapterItem
import com.ugurbuga.followtvmovie.domain.poster.FavoriteUseCase
import com.ugurbuga.followtvmovie.ui.discover.popularlist.PopularListType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TvShowsViewModel @Inject constructor(
    private val favoriteUseCase: FavoriteUseCase,
) : FTMBaseViewModel() {

    val posterList: LiveData<MutableList<ListAdapterItem>>
        get() = favoriteUseCase(FavoriteUseCase.FavoriteParams(PopularListType.TV_SHOW)).asLiveData()

}