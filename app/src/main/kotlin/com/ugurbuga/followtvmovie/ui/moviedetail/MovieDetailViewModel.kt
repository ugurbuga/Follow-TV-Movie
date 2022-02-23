package com.ugurbuga.followtvmovie.ui.moviedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ugurbuga.followtvmovie.base.FTMBaseViewModel
import com.ugurbuga.followtvmovie.domain.favorite.AddFavoriteUseCase
import com.ugurbuga.followtvmovie.domain.favorite.DeleteFavoriteUseCase
import com.ugurbuga.followtvmovie.domain.favorite.GetFavoriteUseCase
import com.ugurbuga.followtvmovie.domain.moviedetail.usecase.GetCastsUseCase
import com.ugurbuga.followtvmovie.domain.moviedetail.usecase.GetExternalUrlsUseCase
import com.ugurbuga.followtvmovie.domain.moviedetail.usecase.GetImagesUseCase
import com.ugurbuga.followtvmovie.domain.moviedetail.usecase.GetTrailersUseCase
import com.ugurbuga.followtvmovie.domain.moviedetail.usecase.MovieDetailUseCase
import com.ugurbuga.followtvmovie.extensions.doOnStatusChanged
import com.ugurbuga.followtvmovie.extensions.doOnSuccess
import com.ugurbuga.followtvmovie.ui.discover.DiscoverType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieDetailUseCase: MovieDetailUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val getFavoriteUseCase: GetFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    private val getTrailersUseCase: GetTrailersUseCase,
    private val getImagesUseCase: GetImagesUseCase,
    private val getCastsUseCase: GetCastsUseCase,
    private val getExternalUrlsUseCase: GetExternalUrlsUseCase,
    savedStateHandle: SavedStateHandle,
) : FTMBaseViewModel() {

    private var _movieDetailViewEvent = MutableSharedFlow<MovieDetailViewEvent>()
    val movieDetailViewEvent: SharedFlow<MovieDetailViewEvent> = _movieDetailViewEvent

    private val _movieDetailViewState = MutableStateFlow(MovieDetailViewState())
    val movieDetailViewState: StateFlow<MovieDetailViewState> get() = _movieDetailViewState

    private var movieId: Int = savedStateHandle["arg_id"] ?: -1

    init {
        getMovieDetail()
        getTrailers()
        getCasts()
        getImages()
        getExternalUrls()
    }

    private fun isFavorite() {
        getFavoriteUseCase(
            GetFavoriteUseCase.GetFavoriteParams(
                DiscoverType.MOVIE, movieId
            )
        ).doOnSuccess {
            _movieDetailViewState.value = _movieDetailViewState.value.copy(
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
            _movieDetailViewState.value = _movieDetailViewState.value.copy(
                movieDetail = it, isFavorite = false
            )
            isFavorite()
        }.launchIn(viewModelScope)
    }

    fun changeFavoriteState() {
        if (movieDetailViewState.value.isFavorite) {
            movieDetailViewState.value.movieDetail?.let {
                deleteFavoriteUseCase(DeleteFavoriteUseCase.DeleteFavoriteParams(it.id)).doOnSuccess {
                    _movieDetailViewEvent.emit(MovieDetailViewEvent.ShowDeletedSnackbar)
                }.launchIn(viewModelScope)
            }
        } else {
            movieDetailViewState.value.movieDetail?.let {
                addFavoriteUseCase(AddFavoriteUseCase.AddFavoriteParams(it)).doOnSuccess {
                    _movieDetailViewEvent.emit(MovieDetailViewEvent.ShowAddedSnackbar)
                }.launchIn(viewModelScope)
            }
        }
    }

    private fun getTrailers() {
        getTrailersUseCase(GetTrailersUseCase.TrailerParams(movieId)).doOnStatusChanged {
            initStatusState(
                it, isShowLoading = false
            )
        }.doOnSuccess {
            _movieDetailViewState.value = movieDetailViewState.value.copy(trailers = it)
            isFavorite()
        }.launchIn(viewModelScope)
    }


    private fun getCasts() {
        getCastsUseCase(GetCastsUseCase.CastParams(movieId)).doOnStatusChanged {
            initStatusState(
                it, isShowLoading = false
            )
        }.doOnSuccess {
            _movieDetailViewState.value = movieDetailViewState.value.copy(casts = it)
            isFavorite()
        }.launchIn(viewModelScope)
    }

    private fun getImages() {
        getImagesUseCase(GetImagesUseCase.ImageParams(movieId)).doOnStatusChanged {
            initStatusState(
                it, isShowLoading = false
            )
        }.doOnSuccess {
            _movieDetailViewState.value = movieDetailViewState.value.copy(images = it)
            isFavorite()
        }.launchIn(viewModelScope)
    }

    private fun getExternalUrls() {
        getExternalUrlsUseCase(GetExternalUrlsUseCase.ExternalUrlParams(movieId)).doOnStatusChanged {
            initStatusState(
                it, isShowLoading = false
            )
        }.doOnSuccess {
            _movieDetailViewState.value = movieDetailViewState.value.copy(externalUrls = it)
            isFavorite()
        }.launchIn(viewModelScope)
    }

    fun reviewsClicked() {
        viewModelScope.launch {
            _movieDetailViewEvent.emit(MovieDetailViewEvent.NavigateToReviews(movieId))
        }
    }

    fun imageClicked(position: Int) {
        viewModelScope.launch {
            _movieDetailViewEvent.emit(
                MovieDetailViewEvent.NavigateToImages(
                    imageList = movieDetailViewState.value.images, position = position
                )
            )
        }
    }

    fun imdbClicked(packageEnabled: Boolean) {
        if (packageEnabled) {
            navigateToOtherApp(movieDetailViewState.value.externalUrls.getImdbUrl())
        } else {
            navigateToWebView(movieDetailViewState.value.externalUrls.getImdbUrl())
        }
    }

    fun facebookClicked(packageEnabled: Boolean) {
        if (packageEnabled) {
            navigateToOtherApp(movieDetailViewState.value.externalUrls.getFacebookDeeplink())
        } else {
            navigateToWebView(movieDetailViewState.value.externalUrls.getFacebookUrl())
        }
    }

    fun twitterClicked(packageEnabled: Boolean) {
        if (packageEnabled) {
            navigateToOtherApp(movieDetailViewState.value.externalUrls.getTwitterUrl())
        } else {
            navigateToWebView(movieDetailViewState.value.externalUrls.getTwitterUrl())
        }
    }

    fun instagramClicked(packageEnabled: Boolean) {
        if (packageEnabled) {
            navigateToOtherApp(movieDetailViewState.value.externalUrls.getInstagramUrl())
        } else {
            navigateToWebView(movieDetailViewState.value.externalUrls.getInstagramUrl())
        }    }

    private fun navigateToOtherApp(url: String) {
        viewModelScope.launch {
            _movieDetailViewEvent.emit(MovieDetailViewEvent.NavigateToOtherApp(url))
        }
    }

    private fun navigateToWebView(url: String) {
        viewModelScope.launch {
            _movieDetailViewEvent.emit(MovieDetailViewEvent.NavigateToWebView(url))
        }
    }

}