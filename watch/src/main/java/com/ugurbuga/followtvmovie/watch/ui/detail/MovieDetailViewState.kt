package com.ugurbuga.followtvmovie.watch.ui.detail

import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.MovieDetailUIModel

data class MovieDetailViewState(
    val movieDetail: MovieDetailUIModel? = null,
    val isFavorite: Boolean = false,
)