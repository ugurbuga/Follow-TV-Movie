package com.ugurbuga.followtvmovie.domain.poster.model

data class PosterUIModel(
    val posterList: MutableList<Any>,
    val page: Int,
    val totalPages: Int,
)