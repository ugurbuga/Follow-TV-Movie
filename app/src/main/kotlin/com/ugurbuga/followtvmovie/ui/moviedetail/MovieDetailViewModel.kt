package com.ugurbuga.followtvmovie.ui.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ugurbuga.followtvmovie.base.FTMBaseViewModel
import com.ugurbuga.followtvmovie.common.Event
import com.ugurbuga.followtvmovie.domain.favorite.AddFavoriteUseCase
import com.ugurbuga.followtvmovie.domain.favorite.DeleteFavoriteUseCase
import com.ugurbuga.followtvmovie.domain.favorite.GetFavoriteUseCase
import com.ugurbuga.followtvmovie.domain.moviedetail.usecase.GetTrailerUseCase
import com.ugurbuga.followtvmovie.domain.moviedetail.usecase.MovieDetailUseCase
import com.ugurbuga.followtvmovie.extensions.doOnStatusChanged
import com.ugurbuga.followtvmovie.extensions.doOnSuccess
import com.ugurbuga.followtvmovie.ui.discover.DiscoverType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieDetailUseCase: MovieDetailUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val getFavoriteUseCase: GetFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    private val getTrailerUseCase: GetTrailerUseCase,
    savedStateHandle: SavedStateHandle,
) : FTMBaseViewModel() {

    private val _movieDetailViewEvent = MutableLiveData<Event<MovieDetailViewEvent>>()
    val movieDetailViewEvent: LiveData<Event<MovieDetailViewEvent>> = _movieDetailViewEvent

    private val _movieDetailViewState = MutableLiveData<MovieDetailViewState>()
    val movieDetailViewState: LiveData<MovieDetailViewState> get() = _movieDetailViewState

    private var movieId: Int = savedStateHandle["arg_id"] ?: -1

    init {
        getMovieDetail()
    }

    private fun isFavorite() {
        getFavoriteUseCase(
            GetFavoriteUseCase.GetFavoriteParams(
                DiscoverType.MOVIE,
                movieId
            )
        ).doOnSuccess {
            _movieDetailViewState.value = _movieDetailViewState.value?.copy(
                isFavorite = it != null
            )
        }.launchIn(viewModelScope)
    }

    private fun getMovieDetail() {
        movieDetailUseCase(MovieDetailUseCase.MovieDetailParams(movieId)).doOnStatusChanged {
            initStatusState(
                it, isShowLoading = false
            )
        }.doOnSuccess {
            _movieDetailViewState.postValue(MovieDetailViewState(it, false))
            isFavorite()
            getTrailers()
        }.launchIn(viewModelScope)
    }

    fun changeFavoriteState() {
        if (movieDetailViewState.value?.isFavorite == true) {
            movieDetailViewState.value?.movieDetail?.let {
                deleteFavoriteUseCase(DeleteFavoriteUseCase.DeleteFavoriteParams(it.id)).doOnSuccess {
                    _movieDetailViewEvent.value = Event(MovieDetailViewEvent.ShowDeletedSnackbar)
                }.launchIn(viewModelScope)
            }
        } else {
            movieDetailViewState.value?.movieDetail?.let {
                addFavoriteUseCase(AddFavoriteUseCase.AddFavoriteParams(it)).doOnSuccess {
                    _movieDetailViewEvent.value = Event(MovieDetailViewEvent.ShowAddedSnackbar)
                }.launchIn(viewModelScope)
            }
        }
    }

    private fun getTrailers() {
        getTrailerUseCase(GetTrailerUseCase.TrailerParams(movieId)).doOnStatusChanged {
            initStatusState(
                it, isShowLoading = false
            )
        }.doOnSuccess {
            movieDetailViewState.value?.apply {
                _movieDetailViewState.value = this.copy(trailers = it)
            }
            isFavorite()
        }.launchIn(viewModelScope)
    }
}