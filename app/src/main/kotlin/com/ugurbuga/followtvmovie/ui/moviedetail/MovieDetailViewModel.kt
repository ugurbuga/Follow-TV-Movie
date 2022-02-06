package com.ugurbuga.followtvmovie.ui.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ugurbuga.followtvmovie.base.FTMBaseViewModel
import com.ugurbuga.followtvmovie.domain.moviedetail.usecase.MovieDetailUseCase
import com.ugurbuga.followtvmovie.domain.poster.AddFavoriteUseCase
import com.ugurbuga.followtvmovie.extensions.doOnStatusChanged
import com.ugurbuga.followtvmovie.extensions.doOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieDetailUseCase: MovieDetailUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    savedStateHandle: SavedStateHandle,
) : FTMBaseViewModel() {

    private val _movieDetailViewState = MutableLiveData<MovieDetailViewState>()
    val movieDetailViewState: LiveData<MovieDetailViewState> get() = _movieDetailViewState

    private var movieId: Int = savedStateHandle["arg_id"] ?: -1

    init {
        getMovieDetail()
    }

    private fun getMovieDetail() {
        movieDetailUseCase(MovieDetailUseCase.PopularMovieParams(movieId))
            .doOnStatusChanged {
                initStatusState(
                    it,
                    isShowLoading = false
                )
            }
            .doOnSuccess {
                _movieDetailViewState.postValue(MovieDetailViewState(it))
            }
            .launchIn(viewModelScope)
    }

    fun addFavorite() {
        movieDetailViewState.value?.movieDetail?.let {
            addFavoriteUseCase(AddFavoriteUseCase.AddFavoriteParams(it))
                .doOnStatusChanged {
                    it
                }.launchIn(viewModelScope)
        }
    }

}