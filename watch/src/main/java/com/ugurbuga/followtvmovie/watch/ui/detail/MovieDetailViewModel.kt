package com.ugurbuga.followtvmovie.watch.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ugurbuga.followtvmovie.core.extensions.doOnSuccess
import com.ugurbuga.followtvmovie.domain.favorite.usecase.AddFavoriteMovieUseCase
import com.ugurbuga.followtvmovie.domain.favorite.usecase.DeleteFavoriteUseCase
import com.ugurbuga.followtvmovie.domain.favorite.usecase.GetFavoriteUseCase
import com.ugurbuga.followtvmovie.domain.moviedetail.usecase.GetMovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val addFavoriteUseCase: AddFavoriteMovieUseCase,
    private val getFavoriteUseCase: GetFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _movieDetailViewState = MutableStateFlow(MovieDetailViewState())
    val movieDetailViewState: StateFlow<MovieDetailViewState> get() = _movieDetailViewState

    private val _movieDetailViewEvent = MutableSharedFlow<MovieDetailViewEvent>()
    val movieDetailViewEvent: SharedFlow<MovieDetailViewEvent> get() = _movieDetailViewEvent

    private var movieId = savedStateHandle.get<String>("movieId").orEmpty()

    init {
        getMovieDetail()
    }

    private fun isFavorite() {
        getFavoriteUseCase(
            GetFavoriteUseCase.GetFavoriteParams(
                MediaType.MOVIE, movieId
            )
        ).doOnSuccess {
            _movieDetailViewState.value = _movieDetailViewState.value.copy(
                isFavorite = it != null
            )
        }.launchIn(viewModelScope)

    }

    private fun getMovieDetail() {
        getMovieDetailUseCase(
            GetMovieDetailUseCase.MovieDetailParams(
                movieId
            )
        ).doOnSuccess {
            _movieDetailViewState.value = _movieDetailViewState.value.copy(
                movieDetail = it, isFavorite = false
            )
            isFavorite()
        }.launchIn(viewModelScope)
    }

    fun tileClicked() {
        if (movieDetailViewState.value.isFavorite) {
            //Remove
            movieDetailViewState.value.movieDetail?.let {
                deleteFavoriteUseCase(DeleteFavoriteUseCase.DeleteFavoriteParams(it.id))
                    .doOnSuccess {
                        _movieDetailViewEvent.emit(MovieDetailViewEvent.RefreshTile)
                    }.launchIn(viewModelScope)
            }
        } else {
            movieDetailViewState.value.movieDetail?.let {
                addFavoriteUseCase(
                    AddFavoriteMovieUseCase.AddFavoriteParams(
                        MediaType.MOVIE,
                        it,
                        false
                    )
                ).doOnSuccess {
                    _movieDetailViewEvent.emit(MovieDetailViewEvent.RefreshTile)
                }.launchIn(viewModelScope)
            }
        }
    }
}