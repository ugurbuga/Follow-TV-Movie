package com.ugurbuga.followtvmovie.ui.moviedetail.review

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ugurbuga.followtvmovie.base.FTMBaseViewModel
import com.ugurbuga.followtvmovie.common.Argument
import com.ugurbuga.followtvmovie.core.common.CommonUtil
import com.ugurbuga.followtvmovie.core.extensions.doOnStatusChanged
import com.ugurbuga.followtvmovie.core.extensions.doOnSuccess
import com.ugurbuga.followtvmovie.domain.moviedetail.usecase.GetReviewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val movieReviewUseCase: GetReviewsUseCase,
    savedStateHandle: SavedStateHandle,
) : FTMBaseViewModel() {

    private val _movieReviewViewState = MutableStateFlow(MovieReviewViewState())
    val movieReviewViewState: StateFlow<MovieReviewViewState> get() = _movieReviewViewState

    private var id: String = savedStateHandle[Argument.ID] ?: CommonUtil.EMPTY_STRING
    private var mediaType: String = savedStateHandle[Argument.MEDIA_TYPE] ?: CommonUtil.EMPTY_STRING

    init {
        getReviews()
    }

    private fun getReviews() {
        movieReviewUseCase(GetReviewsUseCase.ReviewsParams(id,mediaType))
            .doOnStatusChanged {
                initStatusState(
                    it, isShowLoading = false
                )
            }.doOnSuccess {
                _movieReviewViewState.value = MovieReviewViewState(it)
            }.launchIn(viewModelScope)
    }

}