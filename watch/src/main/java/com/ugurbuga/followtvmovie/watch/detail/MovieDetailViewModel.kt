package com.ugurbuga.followtvmovie.watch.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ugurbuga.followtvmovie.watch.detail.model.MovieDetailResponse
import com.ugurbuga.followtvmovie.watch.util.Resource
import com.ugurbuga.followtvmovie.watch.util.Util
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase, savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _movieDetail = MutableStateFlow<MovieDetailResponse?>(null)
    val movieDetail: StateFlow<MovieDetailResponse?> get() = _movieDetail

    private var movieId = savedStateHandle["movieId"] ?: Util.EMPTY_STRING

    init {
        getMovies()
    }

    private fun getMovies() {
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
                        _movieDetail.value = it.data
                    }
                }
            }
        }
    }
}