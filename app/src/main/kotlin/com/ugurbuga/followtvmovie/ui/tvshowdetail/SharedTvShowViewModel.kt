package com.ugurbuga.followtvmovie.ui.tvshowdetail

import com.ugurbuga.followtvmovie.base.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class SharedTvShowViewModel @Inject constructor() : BaseViewModel() {

    private val _tvShowDetailViewState = MutableStateFlow(TvShowDetailViewState())
    val tvShowDetailViewState: StateFlow<TvShowDetailViewState> get() = _tvShowDetailViewState

    fun setTvShowDetailViewState(viewState: TvShowDetailViewState) {
        _tvShowDetailViewState.value = viewState
    }
}