package com.ugurbuga.followtvmovie.domain.moviedetail.model.detail

import com.ugurbuga.followtvmovie.base.adapter.ListAdapterItem

data class CastUIModel(
    val creditId: String,
    val name: String,
    val character: String,
    val profilePath: String
) : ListAdapterItem