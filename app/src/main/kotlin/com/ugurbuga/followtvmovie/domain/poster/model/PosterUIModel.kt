package com.ugurbuga.followtvmovie.domain.poster.model

import com.ugurbuga.followtvmovie.base.adapter.ListAdapterItem

data class PosterUIModel(
    val posterList: MutableList<ListAdapterItem>,
    val page: Int,
    val totalPages: Int,
)