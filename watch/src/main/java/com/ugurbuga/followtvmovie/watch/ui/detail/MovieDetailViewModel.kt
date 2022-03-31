package com.ugurbuga.followtvmovie.watch.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ugurbuga.followtvmovie.watch.util.Resource
import com.ugurbuga.followtvmovie.watch.util.Util
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val getFavoriteUseCase: GetFavoriteUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _movieDetailViewState = MutableStateFlow(MovieDetailViewState())
    val movieDetailViewState: StateFlow<MovieDetailViewState> get() = _movieDetailViewState

    private var movieId = savedStateHandle["movieId"] ?: Util.EMPTY_STRING

    init {
        getMovieDetail()
    }

    private fun isFavorite() {
        viewModelScope.launch {
            getFavoriteUseCase(
                GetFavoriteUseCase.GetFavoriteParams(
                    MediaType.MOVIE, movieId
                )
            ).collect {
                when (it) {
                    is Resource.Error -> {
                    }
                    Resource.Loading -> {
                    }
                    is Resource.Success -> {
                        _movieDetailViewState.value = _movieDetailViewState.value.copy(
                            isFavorite = it.data != null
                        )
                    }
                }
            }
        }
    }

    private fun getMovieDetail() {
        viewModelScope.launch {
            getMovieDetailUseCase(
                GetMovieDetailUseCase.MovieDetailParams(
                    movieId
                )
            ).collect {
                when (it) {
                    is Resource.Error -> {
                    }
                    Resource.Loading -> {
                    }
                    is Resource.Success -> {
                        _movieDetailViewState.value = _movieDetailViewState.value.copy(
                            movieDetail = it.data, isFavorite = false
                        )
                        isFavorite()
                    }
                }
            }
        }
    }
}