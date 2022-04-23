package com.ugurbuga.followtvmovie.ui.moviedetail

import com.ugurbuga.followtvmovie.domain.external.model.ExternalIdsUIModel
import com.ugurbuga.followtvmovie.domain.image.model.ImageUIModel
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.CastUIModel
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.VideoUIModel
import com.ugurbuga.followtvmovie.domain.poster.model.PosterUIModel

data class CommonViewState(
    val isFavorite: Boolean = false,
    val videos: ArrayList<VideoUIModel> = arrayListOf(),
    val casts: ArrayList<CastUIModel> = arrayListOf(),
    val images: ArrayList<ImageUIModel> = arrayListOf(),
    val externalUrls: ExternalIdsUIModel = ExternalIdsUIModel(),
    val recommendation: PosterUIModel = PosterUIModel(),
    val similar: PosterUIModel = PosterUIModel(),
)