package com.ugurbuga.followtvmovie.ui.moviedetail.review

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ugurbuga.followtvmovie.base.FTMBaseViewModel
import com.ugurbuga.followtvmovie.common.Util
import com.ugurbuga.followtvmovie.domain.moviedetail.usecase.GetMovieReviewsUseCase
import com.ugurbuga.followtvmovie.extensions.doOnStatusChanged
import com.ugurbuga.followtvmovie.extensions.doOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn

@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val movieReviewUseCase: GetMovieReviewsUseCase,
    savedStateHandle: SavedStateHandle,
) : FTMBaseViewModel() {

    private val _movieReviewViewState = MutableStateFlow(MovieReviewViewState())
    val movieReviewViewState: StateFlow<MovieReviewViewState> get() = _movieReviewViewState

    private var movieId: String = savedStateHandle["arg_id"] ?: Util.EMPTY_STRING

    init {
        getReviews()
    }

    private fun getReviews() {
        movieReviewUseCase(GetMovieReviewsUseCase.MovieReviewsParams(movieId)).doOnStatusChanged {
            initStatusState(
                it, isShowLoading = false
            )
        }.doOnSuccess {
            _movieReviewViewState.value = MovieReviewViewState(it)
        }.launchIn(viewModelScope)
    }

}