package com.ugurbuga.followtvmovie.ui.moviedetail

import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.MovieDetailUIModel
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.TrailerUIModel

data class MovieDetailViewState(
    val movieDetail: MovieDetailUIModel,
    val isFavorite: Boolean,
    val trailers: ArrayList<TrailerUIModel> = arrayListOf()
)