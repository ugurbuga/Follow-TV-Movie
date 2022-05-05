package com.ugurbuga.followtvmovie.ui.tvshowdetail

import com.ugurbuga.followtvmovie.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SharedTvShowViewModel @Inject constructor() : BaseViewModel() {

    private val _tvShowDetailViewState = MutableStateFlow(TvShowDetailViewState())
    val tvShowDetailViewState: StateFlow<TvShowDetailViewState> get() = _tvShowDetailViewState

    fun setTvShowDetailViewState(viewState: TvShowDetailViewState) {
        _tvShowDetailViewState.value = viewState
    }
}