package com.ugurbuga.followtvmovie.ui.moviedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ugurbuga.followtvmovie.base.FTMBaseViewModel
import com.ugurbuga.followtvmovie.common.Argument
import com.ugurbuga.followtvmovie.core.common.Util
import com.ugurbuga.followtvmovie.core.extensions.doOnStatusChanged
import com.ugurbuga.followtvmovie.core.extensions.doOnSuccess
import com.ugurbuga.followtvmovie.domain.credit.usecase.GetCastsUseCase
import com.ugurbuga.followtvmovie.domain.external.usecase.GetExternalUrlsUseCase
import com.ugurbuga.followtvmovie.domain.favorite.usecase.GetFavoriteUseCase
import com.ugurbuga.followtvmovie.domain.image.usecase.GetImagesUseCase
import com.ugurbuga.followtvmovie.domain.moviedetail.usecase.GetRecommendationsUseCase
import com.ugurbuga.followtvmovie.domain.moviedetail.usecase.GetSimilarUseCase
import com.ugurbuga.followtvmovie.domain.moviedetail.usecase.GetVideosUseCase
import com.ugurbuga.followtvmovie.domain.poster.model.LoadingUIModel
import com.ugurbuga.followtvmovie.domain.poster.model.PosterUIModel
import kotlinx.coroutines.flow.*

abstract class CommonViewModel(
    private val getFavoriteUseCase: GetFavoriteUseCase,
    private val getVideosUseCase: GetVideosUseCase,
    private val getImagesUseCase: GetImagesUseCase,
    private val getCastsUseCase: GetCastsUseCase,
    private val getExternalUrlsUseCase: GetExternalUrlsUseCase,
    private val getRecommendationsUseCase: GetRecommendationsUseCase,
    private val getSimilarUseCase: GetSimilarUseCase,
    savedStateHandle: SavedStateHandle,
) : FTMBaseViewModel() {

    protected var _commonViewEvent = MutableSharedFlow<CommonViewEvent>()
    val commonViewEvent: SharedFlow<CommonViewEvent> = _commonViewEvent

    protected val _commonViewState = MutableStateFlow(CommonViewState())
    val commonViewState: StateFlow<CommonViewState> get() = _commonViewState

    protected var id: String = savedStateHandle[Argument.ID] ?: Util.EMPTY_STRING

    private var isCanLoadNewItemRecommendations = false
    private var isCanLoadNewItemSimilar = false

    abstract fun getMediaType(): String

    init {
        getVideos()
        getCasts()
        getImages()
        getExternalUrls()
        getRecommendations()
        getSimilar()
    }

    fun isFavorite() {
        getFavoriteUseCase(
            GetFavoriteUseCase.GetFavoriteParams(
                getMediaType(), id
            )
        ).doOnSuccess {
            _commonViewState.value = commonViewState.value.copy(
                isFavorite = it != null
            )
        }.launchIn(viewModelScope)
    }

    private fun getVideos() {
        getVideosUseCase(GetVideosUseCase.VideoParams(id, getMediaType()))
            .doOnStatusChanged {
                initStatusState(
                    it, isShowLoading = false
                )
            }.doOnSuccess {
                _commonViewState.value = commonViewState.value.copy(videos = it)
            }.launchIn(viewModelScope)
    }

    private fun getCasts() {
        getCastsUseCase(GetCastsUseCase.CastParams(id, getMediaType()))
            .doOnStatusChanged {
                initStatusState(
                    it, isShowLoading = false
                )
            }.doOnSuccess {
                _commonViewState.value = commonViewState.value.copy(casts = it)
            }.launchIn(viewModelScope)
    }

    private fun getImages() {
        getImagesUseCase(GetImagesUseCase.ImageParams(id, getMediaType()))
            .doOnStatusChanged {
                initStatusState(
                    it, isShowLoading = false
                )
            }.doOnSuccess {
                _commonViewState.value = commonViewState.value.copy(images = it)
            }.launchIn(viewModelScope)
    }

    private fun getExternalUrls() {
        getExternalUrlsUseCase(GetExternalUrlsUseCase.ExternalUrlParams(id, getMediaType()))
            .doOnStatusChanged {
                initStatusState(
                    it, isShowLoading = false
                )
            }.doOnSuccess {
                _commonViewState.value = commonViewState.value.copy(externalUrls = it)
            }.launchIn(viewModelScope)
    }

    fun reviewsClicked() {
        _commonViewEvent.emitSuspending(CommonViewEvent.NavigateToReviews(id))

    }

    fun imageClicked(position: Int) {
        _commonViewEvent.emitSuspending(
            CommonViewEvent.NavigateToImages(
                imageList = commonViewState.value.images, position = position
            )
        )
    }

    fun imdbClicked(packageEnabled: Boolean) {
        if (packageEnabled) {
            navigateToOtherApp(commonViewState.value.externalUrls.getImdbUrl())
        } else {
            navigateToWebView(commonViewState.value.externalUrls.getImdbUrl())
        }
    }

    fun facebookClicked(packageEnabled: Boolean) {
        if (packageEnabled) {
            navigateToOtherApp(commonViewState.value.externalUrls.getFacebookDeeplink())
        } else {
            navigateToWebView(commonViewState.value.externalUrls.getFacebookUrl())
        }
    }

    fun twitterClicked(packageEnabled: Boolean) {
        if (packageEnabled) {
            navigateToOtherApp(commonViewState.value.externalUrls.getTwitterUrl())
        } else {
            navigateToWebView(commonViewState.value.externalUrls.getTwitterUrl())
        }
    }

    fun instagramClicked(packageEnabled: Boolean) {
        if (packageEnabled) {
            navigateToOtherApp(commonViewState.value.externalUrls.getInstagramUrl())
        } else {
            navigateToWebView(commonViewState.value.externalUrls.getInstagramUrl())
        }
    }

    private fun navigateToOtherApp(url: String) {
        _commonViewEvent.emitSuspending(CommonViewEvent.NavigateToOtherApp(url))
    }

    private fun navigateToWebView(url: String) {
        _commonViewEvent.emitSuspending(CommonViewEvent.NavigateToWebView(url))
    }

    private fun getRecommendations() {
        addRecommendationLoading()
        getRecommendationsUseCase(
            GetRecommendationsUseCase.Recommendations(
                id,
                ++commonViewState.value.recommendation.page,
                getMediaType()
            )
        ).doOnStatusChanged {
            initStatusState(
                it, isShowLoading = false
            )
        }.doOnSuccess {
            setRecommendationList(it)
            isCanLoadNewItemRecommendations = it.totalPages > it.page
        }.launchIn(viewModelScope)
    }

    fun getNewRecommendations() {
        if (isCanLoadNewItemRecommendations) {
            isCanLoadNewItemRecommendations = false
            getRecommendations()
        }
    }

    private fun addRecommendationLoading() {
        val list = commonViewState.value.recommendation.posterList.toMutableList()
        list.add(LoadingUIModel())
        val recommendation = commonViewState.value.recommendation.copy()
        recommendation.posterList = list
        _commonViewState.value =
            commonViewState.value.copy(recommendation = recommendation)
    }

    private fun setRecommendationList(recommendation: PosterUIModel) {
        val list = commonViewState.value.recommendation.posterList.toMutableList()
        list.remove(LoadingUIModel())
        list.addAll(recommendation.posterList)
        recommendation.posterList = list
        _commonViewState.value =
            commonViewState.value.copy(recommendation = recommendation)
    }

    /////////////

    private fun getSimilar() {
        addSimilarLoading()
        getSimilarUseCase(
            GetSimilarUseCase.Similar(
                id,
                ++commonViewState.value.similar.page,
                getMediaType()
            )
        ).doOnStatusChanged {
            initStatusState(
                it, isShowLoading = false
            )
        }.doOnSuccess {
            setSimilarList(it)
            isCanLoadNewItemSimilar = it.totalPages > it.page
        }.launchIn(viewModelScope)
    }

    fun getNewSimilar() {
        if (isCanLoadNewItemSimilar) {
            isCanLoadNewItemSimilar = false
            getSimilar()
        }
    }

    private fun addSimilarLoading() {
        val list = commonViewState.value.similar.posterList.toMutableList()
        list.add(LoadingUIModel())
        val similar = commonViewState.value.similar.copy()
        similar.posterList = list
        _commonViewState.value =
            commonViewState.value.copy(similar = similar)
    }

    private fun setSimilarList(similar: PosterUIModel) {
        val list = commonViewState.value.similar.posterList.toMutableList()
        list.remove(LoadingUIModel())
        list.addAll(similar.posterList)
        similar.posterList = list
        _commonViewState.value =
            commonViewState.value.copy(similar = similar)
    }
}