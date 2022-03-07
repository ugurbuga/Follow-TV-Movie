package com.ugurbuga.followtvmovie.ui.moviedetail

import com.ugurbuga.followtvmovie.domain.moviedetail.image.ImageUIModel

sealed class MovieDetailViewEvent {

    data class ShowSnackbar(val message: Int) : MovieDetailViewEvent()

    data class NavigateToReviews(val movieId: String) : MovieDetailViewEvent()

    data class NavigateToImages(val imageList: ArrayList<ImageUIModel>, val position: Int) :
        MovieDetailViewEvent()

    data class NavigateToOtherApp(val url: String) : MovieDetailViewEvent()

    data class NavigateToWebView(val url: String) : MovieDetailViewEvent()

}
