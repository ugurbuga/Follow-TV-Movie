package com.ugurbuga.followtvmovie.ui.search

import androidx.lifecycle.viewModelScope
import com.ugurbuga.followtvmovie.base.FTMBaseViewModel
import com.ugurbuga.followtvmovie.domain.populartvshow.PopularTvShowUseCase
import com.ugurbuga.followtvmovie.extensions.doOnStatusChanged
import com.ugurbuga.followtvmovie.extensions.doOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val tvShowUseCase: PopularTvShowUseCase
) : FTMBaseViewModel() {

    init {
        getPopularTvShows()
    }

    private fun getPopularTvShows() {
        tvShowUseCase.getPopularTvShows(1)
            .doOnStatusChanged {
                initStatusState(
                    it
                )
            }
            .doOnSuccess {
            }
            .launchIn(viewModelScope)
    }
}