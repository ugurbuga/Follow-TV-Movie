package com.ugurbuga.followtvmovie.domain.credit.model

import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel


data class CreditDetailUIModel(
    val name: String,
    val profilePath: String,
    val knownFor: List<PosterItemUIModel>
)