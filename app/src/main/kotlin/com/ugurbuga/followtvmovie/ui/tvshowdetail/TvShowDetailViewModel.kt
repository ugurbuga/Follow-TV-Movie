package com.ugurbuga.followtvmovie.ui.tvshowdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.common.FTMUtil
import com.ugurbuga.followtvmovie.core.common.CommonUtil
import com.ugurbuga.followtvmovie.core.extensions.doOnStatusChanged
import com.ugurbuga.followtvmovie.core.extensions.doOnSuccess
import com.ugurbuga.followtvmovie.domain.credit.usecase.GetCastsUseCase
import com.ugurbuga.followtvmovie.domain.external.usecase.GetExternalUrlsUseCase
import com.ugurbuga.followtvmovie.domain.favorite.usecase.AddFavoriteTvShowUseCase
import com.ugurbuga.followtvmovie.domain.favorite.usecase.DeleteFavoriteUseCase
import com.ugurbuga.followtvmovie.domain.favorite.usecase.GetFavoriteUseCase
import com.ugurbuga.followtvmovie.domain.image.usecase.GetImagesUseCase
import com.ugurbuga.followtvmovie.domain.moviedetail.usecase.GetRecommendationsUseCase
import com.ugurbuga.followtvmovie.domain.moviedetail.usecase.GetSimilarUseCase
import com.ugurbuga.followtvmovie.domain.moviedetail.usecase.GetVideosUseCase
import com.ugurbuga.followtvmovie.domain.tvshowdetail.usecase.GetTvShowDetailUseCase
import com.ugurbuga.followtvmovie.ui.discover.MediaType
import com.ugurbuga.followtvmovie.ui.moviedetail.CommonViewEvent
import com.ugurbuga.followtvmovie.ui.moviedetail.CommonViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn

@HiltViewModel
class TvShowDetailViewModel @Inject constructor(
    private val getTvShowDetailUseCase: GetTvShowDetailUseCase,
    private val addFavoriteUseCase: AddFavoriteTvShowUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    getFavoriteUseCase: GetFavoriteUseCase,
    getVideosUseCase: GetVideosUseCase,
    getImagesUseCase: GetImagesUseCase,
    getCastsUseCase: GetCastsUseCase,
    getExternalUrlsUseCase: GetExternalUrlsUseCase,
    getRecommendationsUseCase: GetRecommendationsUseCase,
    getSimilarUseCase: GetSimilarUseCase,
    savedStateHandle: SavedStateHandle,
) : CommonViewModel(
    getFavoriteUseCase,
    getVideosUseCase,
    getImagesUseCase,
    getCastsUseCase,
    getExternalUrlsUseCase,
    getRecommendationsUseCase,
    getSimilarUseCase,
    savedStateHandle
) {

    private val _tvShowDetailViewState = MutableStateFlow(TvShowDetailViewState())
    val tvShowDetailViewState: StateFlow<TvShowDetailViewState> get() = _tvShowDetailViewState

    override fun getMediaType() = MediaType.TV

    init {
        getTvShowDetail()
    }

    private fun getTvShowDetail() {
        getTvShowDetailUseCase(GetTvShowDetailUseCase.TvShowDetailParams(id))
            .doOnStatusChanged {
                initStatusState(
                    it, isShowLoading = false
                )
            }.doOnSuccess {
                _tvShowDetailViewState.value = _tvShowDetailViewState.value.copy(
                    tvShowDetail = it
                )
                isFavorite()
            }.launchIn(viewModelScope)
    }

    fun changeFavoriteState() {
        if (commonViewState.value.isFavorite) {
            tvShowDetailViewState.value.tvShowDetail?.let {
                deleteFavoriteUseCase(DeleteFavoriteUseCase.DeleteFavoriteParams(it.id)).doOnSuccess {
                    _commonViewEvent.emit(CommonViewEvent.ShowSnackbar(R.string.removed_movie_list))
                }.launchIn(viewModelScope)
            }
        } else {
            val isReleased =
                FTMUtil.isReleased(tvShowDetailViewState.value.tvShowDetail?.releaseDateLong)
            if (isReleased) {
                _commonViewEvent.emitSuspending(
                    CommonViewEvent.ShowWatchedOrWatchLaterDialog(
                        tvShowDetailViewState.value.tvShowDetail?.title ?: CommonUtil.EMPTY_STRING
                    )
                )

            } else {
                addFavorite(isWatched = false)
            }

        }
    }

    fun addFavorite(isWatched: Boolean) {
        val message =
            if (isWatched) R.string.added_watched_list else R.string.added_watch_later_list
        tvShowDetailViewState.value.tvShowDetail?.let {
            addFavoriteUseCase(
                AddFavoriteTvShowUseCase.AddFavoriteParams(
                    MediaType.TV,
                    it,
                    isWatched
                )
            )
                .doOnSuccess {
                    _commonViewEvent.emit(CommonViewEvent.ShowSnackbar(message))
                }.launchIn(viewModelScope)
        }
    }
}