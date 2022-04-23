package com.ugurbuga.followtvmovie.ui.tvshowdetail

import com.ugurbuga.followtvmovie.domain.image.model.ImageUIModel

sealed class TvShowDetailViewEvent {

    data class ShowSnackbar(val message: Int) : TvShowDetailViewEvent()

    data class NavigateToReviews(val tvShowId: String) : TvShowDetailViewEvent()

    data class NavigateToImages(val imageList: ArrayList<ImageUIModel>, val position: Int) :
        TvShowDetailViewEvent()

    data class NavigateToOtherApp(val url: String) : TvShowDetailViewEvent()

    data class NavigateToWebView(val url: String) : TvShowDetailViewEvent()

    data class ShowWatchedOrWatchLaterDialog(val tvShowName: String) : TvShowDetailViewEvent()

}
