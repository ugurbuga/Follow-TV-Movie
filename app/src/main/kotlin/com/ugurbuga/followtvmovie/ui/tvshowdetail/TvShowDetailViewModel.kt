package com.ugurbuga.followtvmovie.ui.tvshowdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseViewModel
import com.ugurbuga.followtvmovie.common.Argument
import com.ugurbuga.followtvmovie.common.Util
import com.ugurbuga.followtvmovie.domain.favorite.AddFavoriteTvShowUseCase
import com.ugurbuga.followtvmovie.domain.favorite.DeleteFavoriteUseCase
import com.ugurbuga.followtvmovie.domain.favorite.GetFavoriteUseCase
import com.ugurbuga.followtvmovie.domain.moviedetail.usecase.GetExternalUrlsUseCase
import com.ugurbuga.followtvmovie.domain.moviedetail.usecase.GetImagesUseCase
import com.ugurbuga.followtvmovie.domain.poster.model.LoadingUIModel
import com.ugurbuga.followtvmovie.domain.poster.model.PosterUIModel
import com.ugurbuga.followtvmovie.domain.tvshowdetail.usecase.GetSimilarTvShowsUseCase
import com.ugurbuga.followtvmovie.domain.tvshowdetail.usecase.GetTvShowCastsUseCase
import com.ugurbuga.followtvmovie.domain.tvshowdetail.usecase.GetTvShowDetailUseCase
import com.ugurbuga.followtvmovie.domain.tvshowdetail.usecase.GetTvShowRecommendationsUseCase
import com.ugurbuga.followtvmovie.domain.tvshowdetail.usecase.GetTvShowTrailersUseCase
import com.ugurbuga.followtvmovie.extensions.doOnStatusChanged
import com.ugurbuga.followtvmovie.extensions.doOnSuccess
import com.ugurbuga.followtvmovie.ui.discover.MediaType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn

@HiltViewModel
class TvShowDetailViewModel @Inject constructor(
    private val getTvShowDetailUseCase: GetTvShowDetailUseCase,
    private val addFavoriteUseCase: AddFavoriteTvShowUseCase,
    private val getFavoriteUseCase: GetFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    private val getTvShowTrailersUseCase: GetTvShowTrailersUseCase,
    private val getImagesUseCase: GetImagesUseCase,
    private val getTvShowCastsUseCase: GetTvShowCastsUseCase,
    private val getExternalUrlsUseCase: GetExternalUrlsUseCase,
    private val getTvShowRecommendationsUseCase: GetTvShowRecommendationsUseCase,
    private val getSimilarTvShowsUseCase: GetSimilarTvShowsUseCase,
    savedStateHandle: SavedStateHandle,
) : FTMBaseViewModel() {

    private var _tvShowDetailViewEvent = MutableSharedFlow<TvShowDetailViewEvent>()
    val tvShowDetailViewEvent: SharedFlow<TvShowDetailViewEvent> = _tvShowDetailViewEvent

    private val _tvShowDetailViewState = MutableStateFlow(TvShowDetailViewState())
    val tvShowDetailViewState: StateFlow<TvShowDetailViewState> get() = _tvShowDetailViewState

    private var tvShowId: String = savedStateHandle[Argument.ID] ?: Util.EMPTY_STRING

    private var isCanLoadNewItemRecommendations = false
    private var isCanLoadNewItemSimilarTvShows = false

    init {
        getTvShowDetail()
        getTrailers()
        getCasts()
        getImages()
        getExternalUrls()
        getRecommendations()
        getSimilarTvShows()
    }

    private fun isFavorite() {
        getFavoriteUseCase(
            GetFavoriteUseCase.GetFavoriteParams(
                MediaType.TV, tvShowId
            )
        ).doOnSuccess {
            _tvShowDetailViewState.value = _tvShowDetailViewState.value.copy(
                isFavorite = it != null
            )
        }.launchIn(viewModelScope)
    }

    private fun getTvShowDetail() {
        getTvShowDetailUseCase(GetTvShowDetailUseCase.TvShowDetailParams(tvShowId))
            .doOnStatusChanged {
                initStatusState(
                    it, isShowLoading = false
                )
            }.doOnSuccess {
                _tvShowDetailViewState.value = _tvShowDetailViewState.value.copy(
                    tvShowDetail = it, isFavorite = false
                )
                isFavorite()
            }.launchIn(viewModelScope)
    }

    fun changeFavoriteState() {
        if (tvShowDetailViewState.value.isFavorite) {
            tvShowDetailViewState.value.tvShowDetail?.let {
                deleteFavoriteUseCase(DeleteFavoriteUseCase.DeleteFavoriteParams(it.id)).doOnSuccess {
                    _tvShowDetailViewEvent.emit(TvShowDetailViewEvent.ShowSnackbar(R.string.removed_movie_list))
                }.launchIn(viewModelScope)
            }
        } else {
            val isReleased =
                Util.isReleased(tvShowDetailViewState.value.tvShowDetail?.releaseDateLong)
            if (isReleased) {
                _tvShowDetailViewEvent.emitSuspending(
                    TvShowDetailViewEvent.ShowWatchedOrWatchLaterDialog(
                        tvShowDetailViewState.value.tvShowDetail?.title ?: Util.EMPTY_STRING
                    )
                )

            } else {
                addFavorite(isWatched = false)
            }

        }
    }

    private fun getTrailers() {
        getTvShowTrailersUseCase(GetTvShowTrailersUseCase.TvShowTrailerParams(tvShowId))
            .doOnStatusChanged {
                initStatusState(
                    it, isShowLoading = false
                )
            }.doOnSuccess {
                _tvShowDetailViewState.value = tvShowDetailViewState.value.copy(trailers = it)
                isFavorite()
            }.launchIn(viewModelScope)
    }

    private fun getCasts() {
        getTvShowCastsUseCase(GetTvShowCastsUseCase.CastParams(tvShowId))
            .doOnStatusChanged {
                initStatusState(
                    it, isShowLoading = false
                )
            }.doOnSuccess {
                _tvShowDetailViewState.value = tvShowDetailViewState.value.copy(casts = it)
                isFavorite()
            }.launchIn(viewModelScope)
    }

    private fun getImages() {
        getImagesUseCase(GetImagesUseCase.ImageParams(tvShowId, MediaType.TV))
            .doOnStatusChanged {
                initStatusState(
                    it, isShowLoading = false
                )
            }.doOnSuccess {
                _tvShowDetailViewState.value = tvShowDetailViewState.value.copy(images = it)
                isFavorite()
            }.launchIn(viewModelScope)
    }

    private fun getExternalUrls() {
        getExternalUrlsUseCase(GetExternalUrlsUseCase.ExternalUrlParams(tvShowId, MediaType.TV))
            .doOnStatusChanged {
                initStatusState(
                    it, isShowLoading = false
                )
            }.doOnSuccess {
                _tvShowDetailViewState.value = tvShowDetailViewState.value.copy(externalUrls = it)
                isFavorite()
            }.launchIn(viewModelScope)
    }

    fun reviewsClicked() {
        _tvShowDetailViewEvent.emitSuspending(TvShowDetailViewEvent.NavigateToReviews(tvShowId))

    }

    fun imageClicked(position: Int) {
        _tvShowDetailViewEvent.emitSuspending(
            TvShowDetailViewEvent.NavigateToImages(
                imageList = tvShowDetailViewState.value.images, position = position
            )
        )
    }

    fun imdbClicked(packageEnabled: Boolean) {
        if (packageEnabled) {
            navigateToOtherApp(tvShowDetailViewState.value.externalUrls.getImdbUrl())
        } else {
            navigateToWebView(tvShowDetailViewState.value.externalUrls.getImdbUrl())
        }
    }

    fun facebookClicked(packageEnabled: Boolean) {
        if (packageEnabled) {
            navigateToOtherApp(tvShowDetailViewState.value.externalUrls.getFacebookDeeplink())
        } else {
            navigateToWebView(tvShowDetailViewState.value.externalUrls.getFacebookUrl())
        }
    }

    fun twitterClicked(packageEnabled: Boolean) {
        if (packageEnabled) {
            navigateToOtherApp(tvShowDetailViewState.value.externalUrls.getTwitterUrl())
        } else {
            navigateToWebView(tvShowDetailViewState.value.externalUrls.getTwitterUrl())
        }
    }

    fun instagramClicked(packageEnabled: Boolean) {
        if (packageEnabled) {
            navigateToOtherApp(tvShowDetailViewState.value.externalUrls.getInstagramUrl())
        } else {
            navigateToWebView(tvShowDetailViewState.value.externalUrls.getInstagramUrl())
        }
    }

    private fun navigateToOtherApp(url: String) {
        _tvShowDetailViewEvent.emitSuspending(TvShowDetailViewEvent.NavigateToOtherApp(url))
    }

    private fun navigateToWebView(url: String) {
        _tvShowDetailViewEvent.emitSuspending(TvShowDetailViewEvent.NavigateToWebView(url))
    }

    fun addFavorite(isWatched: Boolean) {
        val message =
            if (isWatched) R.string.added_watched_list else R.string.added_watch_later_list
        tvShowDetailViewState.value.tvShowDetail?.let {
            addFavoriteUseCase(AddFavoriteTvShowUseCase.AddFavoriteParams(it, isWatched))
                .doOnSuccess {
                    _tvShowDetailViewEvent.emit(TvShowDetailViewEvent.ShowSnackbar(message))
                }.launchIn(viewModelScope)
        }
    }

    private fun getRecommendations() {
        addRecommendationLoading()
        getTvShowRecommendationsUseCase(
            GetTvShowRecommendationsUseCase.Recommendations(
                tvShowId,
                ++tvShowDetailViewState.value.recommendation.page
            )
        ).doOnStatusChanged {
            initStatusState(
                it, isShowLoading = false
            )
        }.doOnSuccess {
            setRecommendationList(it)
            isCanLoadNewItemRecommendations = it.totalPages > it.page
            isFavorite()
        }.launchIn(viewModelScope)
    }

    fun getNewRecommendations() {
        if (isCanLoadNewItemRecommendations) {
            isCanLoadNewItemRecommendations = false
            getRecommendations()
        }
    }

    private fun addRecommendationLoading() {
        val list = tvShowDetailViewState.value.recommendation.posterList.toMutableList()
        list.add(LoadingUIModel())
        val recommendation = tvShowDetailViewState.value.recommendation.copy()
        recommendation.posterList = list
        _tvShowDetailViewState.value =
            tvShowDetailViewState.value.copy(recommendation = recommendation)
    }

    private fun setRecommendationList(recommendation: PosterUIModel) {
        val list = tvShowDetailViewState.value.recommendation.posterList.toMutableList()
        list.remove(LoadingUIModel())
        list.addAll(recommendation.posterList)
        recommendation.posterList = list
        _tvShowDetailViewState.value =
            tvShowDetailViewState.value.copy(recommendation = recommendation)
    }

    /////////////

    private fun getSimilarTvShows() {
        addSimilarTvShowsLoading()
        getSimilarTvShowsUseCase(
            GetSimilarTvShowsUseCase.SimilarTvShow(
                tvShowId,
                ++tvShowDetailViewState.value.similarTvShow.page
            )
        ).doOnStatusChanged {
            initStatusState(
                it, isShowLoading = false
            )
        }.doOnSuccess {
            setSimilarTvShowList(it)
            isCanLoadNewItemSimilarTvShows = it.totalPages > it.page
            isFavorite()
        }.launchIn(viewModelScope)
    }

    fun getNewSimilarTvShows() {
        if (isCanLoadNewItemSimilarTvShows) {
            isCanLoadNewItemSimilarTvShows = false
            getSimilarTvShows()
        }
    }

    private fun addSimilarTvShowsLoading() {
        val list = tvShowDetailViewState.value.similarTvShow.posterList.toMutableList()
        list.add(LoadingUIModel())
        val similarTvShow = tvShowDetailViewState.value.similarTvShow.copy()
        similarTvShow.posterList = list
        _tvShowDetailViewState.value =
            tvShowDetailViewState.value.copy(similarTvShow = similarTvShow)
    }

    private fun setSimilarTvShowList(similarTvShow: PosterUIModel) {
        val list = tvShowDetailViewState.value.similarTvShow.posterList.toMutableList()
        list.remove(LoadingUIModel())
        list.addAll(similarTvShow.posterList)
        similarTvShow.posterList = list
        _tvShowDetailViewState.value =
            tvShowDetailViewState.value.copy(similarTvShow = similarTvShow)
    }
}