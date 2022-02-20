package com.ugurbuga.followtvmovie.ui.moviedetail

import com.ugurbuga.followtvmovie.domain.moviedetail.image.ImageUIModel
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.CastUIModel
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.MovieDetailUIModel
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.TrailerUIModel

data class MovieDetailViewState(
    val movieDetail: MovieDetailUIModel? = null,
    val isFavorite: Boolean = false,
    val trailers: ArrayList<TrailerUIModel> = arrayListOf(),
    val casts: ArrayList<CastUIModel> = arrayListOf(),
    val images: ArrayList<ImageUIModel> = arrayListOf()
)