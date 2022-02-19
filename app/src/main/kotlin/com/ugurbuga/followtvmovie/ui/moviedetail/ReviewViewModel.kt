package com.ugurbuga.followtvmovie.ui.moviedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ugurbuga.followtvmovie.base.FTMBaseViewModel
import com.ugurbuga.followtvmovie.domain.moviedetail.usecase.MovieReviewUseCase
import com.ugurbuga.followtvmovie.extensions.doOnStatusChanged
import com.ugurbuga.followtvmovie.extensions.doOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val movieReviewUseCase: MovieReviewUseCase,
    savedStateHandle: SavedStateHandle,
) : FTMBaseViewModel() {

    private val _movieReviewViewState = MutableStateFlow(MovieReviewViewState())
    val movieReviewViewState: StateFlow<MovieReviewViewState> get() = _movieReviewViewState

    private var movieId: Int = savedStateHandle["arg_id"] ?: -1

    init {
        getReviews()
    }

    private fun getReviews() {
        movieReviewUseCase(MovieReviewUseCase.MovieReviewParams(movieId)).doOnStatusChanged {
            initStatusState(
                it, isShowLoading = false
            )
        }.doOnSuccess {
            _movieReviewViewState.value = MovieReviewViewState(it)
        }.launchIn(viewModelScope)
    }

}