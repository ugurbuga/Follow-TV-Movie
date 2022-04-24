package com.ugurbuga.followtvmovie.ui.moviedetail

import com.ugurbuga.followtvmovie.domain.image.model.ImageUIModel

sealed class CommonViewEvent {

    data class ShowSnackbar(val message: Int) : CommonViewEvent()

    data class NavigateToReviews(val id: String) : CommonViewEvent()

    data class NavigateToImages(val imageList: ArrayList<ImageUIModel>, val position: Int) :
        CommonViewEvent()

    data class NavigateToOtherApp(val url: String) : CommonViewEvent()

    data class NavigateToWebView(val url: String) : CommonViewEvent()

    data class ShowWatchedOrWatchLaterDialog(val name: String) : CommonViewEvent()

}
