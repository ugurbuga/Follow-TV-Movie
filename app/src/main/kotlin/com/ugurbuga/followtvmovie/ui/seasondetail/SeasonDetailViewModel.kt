package com.ugurbuga.followtvmovie.ui.seasondetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ugurbuga.followtvmovie.base.FTMBaseViewModel
import com.ugurbuga.followtvmovie.common.Argument
import com.ugurbuga.followtvmovie.core.common.CommonUtil
import com.ugurbuga.followtvmovie.core.extensions.doOnStatusChanged
import com.ugurbuga.followtvmovie.core.extensions.doOnSuccess
import com.ugurbuga.followtvmovie.domain.seasondetail.usecase.GetTvShowSeasonDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn

@HiltViewModel
open class SeasonDetailViewModel @Inject constructor(
    private val getTvShowSeasonDetailUseCase: GetTvShowSeasonDetailUseCase,
    savedStateHandle: SavedStateHandle
) : FTMBaseViewModel() {

    private var id: String = savedStateHandle[Argument.ID] ?: CommonUtil.EMPTY_STRING
    private var imageUrl: String = savedStateHandle[Argument.IMAGE_URL] ?: CommonUtil.EMPTY_STRING
    private var seasonNumber: Int =
        savedStateHandle[Argument.SEASON_NUMBER] ?: CommonUtil.INVALID_INDEX

    private val _seasonDetailViewState = MutableStateFlow(SeasonDetailViewState())
    val seasonDetailViewState: StateFlow<SeasonDetailViewState> get() = _seasonDetailViewState

    init {
        getTvShowSeasonDetail()
    }

    private fun getTvShowSeasonDetail() {
        getTvShowSeasonDetailUseCase(
            GetTvShowSeasonDetailUseCase.TvShowSeasonDetailParams(
                id,
                seasonNumber
            )
        ).doOnStatusChanged { initStatusState(it) }
            .doOnSuccess {
                _seasonDetailViewState.value = SeasonDetailViewState(it, imageUrl)
            }
            .launchIn(viewModelScope)
    }
}