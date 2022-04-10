package com.ugurbuga.followtvmovie.watch.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ugurbuga.followtvmovie.watch.domain.detail.AddFavoriteUseCase
import com.ugurbuga.followtvmovie.watch.domain.detail.DeleteFavoriteUseCase
import com.ugurbuga.followtvmovie.watch.domain.detail.GetFavoriteUseCase
import com.ugurbuga.followtvmovie.watch.domain.detail.GetMovieDetailUseCase
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
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val getFavoriteUseCase: GetFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
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

    fun tileClicked() {
        if (movieDetailViewState.value.isFavorite) {
            //Remove
            viewModelScope.launch {
                movieDetailViewState.value.movieDetail?.let {
                    deleteFavoriteUseCase(DeleteFavoriteUseCase.DeleteFavoriteParams(it.id))
                }
            }
        } else {
            viewModelScope.launch {
                movieDetailViewState.value.movieDetail?.let {
                    addFavoriteUseCase(AddFavoriteUseCase.AddFavoriteParams(it, false))
                }
            }
        }
    }
}