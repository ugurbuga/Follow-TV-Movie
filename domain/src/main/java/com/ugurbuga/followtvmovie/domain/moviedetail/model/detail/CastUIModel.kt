package com.ugurbuga.followtvmovie.domain.moviedetail.model.detail

import com.ugurbuga.followtvmovie.core.adapter.ListAdapterItem

data class CastUIModel(
    val id: String,
    val name: String,
    val character: String,
    val profilePath: String,
    val mediaType: String
) : ListAdapterItem