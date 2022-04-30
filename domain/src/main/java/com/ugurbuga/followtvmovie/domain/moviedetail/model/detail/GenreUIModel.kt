package com.ugurbuga.followtvmovie.domain.moviedetail.model.detail

import com.ugurbuga.followtvmovie.core.adapter.ListAdapterItem


data class GenreUIModel(
    val id: String,
    val name: String
) : ListAdapterItem