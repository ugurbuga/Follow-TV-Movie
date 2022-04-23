package com.ugurbuga.followtvmovie.ui.moviedetail

import com.ugurbuga.followtvmovie.domain.moviedetail.external.ExternalIdsUIModel
import com.ugurbuga.followtvmovie.domain.moviedetail.image.ImageUIModel
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.CastUIModel
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.MovieDetailUIModel
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.VideoUIModel
import com.ugurbuga.followtvmovie.domain.poster.model.PosterUIModel

data class MovieDetailViewState(
    val movieDetail: MovieDetailUIModel? = null,
    val isFavorite: Boolean = false,
    val videos: ArrayList<VideoUIModel> = arrayListOf(),
    val casts: ArrayList<CastUIModel> = arrayListOf(),
    val images: ArrayList<ImageUIModel> = arrayListOf(),
    val externalUrls: ExternalIdsUIModel = ExternalIdsUIModel(),
    val recommendation: PosterUIModel = PosterUIModel(),
    val similarMovie: PosterUIModel = PosterUIModel(),
)