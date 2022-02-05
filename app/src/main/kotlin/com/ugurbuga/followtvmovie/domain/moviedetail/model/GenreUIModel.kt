package com.ugurbuga.followtvmovie.domain.moviedetail.model

import com.ugurbuga.followtvmovie.base.adapter.ListAdapterItem


data class GenreUIModel(
    val id: Int,
    val name: String
) : ListAdapterItem