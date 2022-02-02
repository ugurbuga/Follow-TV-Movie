package com.ugurbuga.followtvmovie.ui.discover.popular.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ugurbuga.followtvmovie.base.FTMBaseViewModel
import com.ugurbuga.followtvmovie.domain.populartvshow.model.PosterUIModel
import com.ugurbuga.followtvmovie.domain.populartvshow.usecase.PopularTvShowUseCase
import com.ugurbuga.followtvmovie.extensions.doOnStatusChanged
import com.ugurbuga.followtvmovie.extensions.doOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
class PopularTvShowViewModel @Inject constructor(
    private val tvShowUseCase: PopularTvShowUseCase
) : FTMBaseViewModel() {


    private val _poster = MutableLiveData<PosterUIModel>()
    val poster: LiveData<PosterUIModel> get() = _poster

    init {
        getPopularTvShows()
    }

    private fun getPopularTvShows() {
        tvShowUseCase(PopularTvShowUseCase.PopularTvShowParams(1))
            .doOnStatusChanged {
                initStatusState(
                    it,
                    isShowLoading = false
                )
            }
            .doOnSuccess {
                _poster.value = it
            }
            .launchIn(viewModelScope)
    }
}