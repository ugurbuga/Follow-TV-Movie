package com.ugurbuga.followtvmovie.domain.poster.model

import com.ugurbuga.followtvmovie.core.adapter.ListAdapterItem

data class PosterUIModel(
    var posterList: MutableList<ListAdapterItem> = arrayListOf(),
    var page: Int = 0,
    val totalPages: Int = 0
)