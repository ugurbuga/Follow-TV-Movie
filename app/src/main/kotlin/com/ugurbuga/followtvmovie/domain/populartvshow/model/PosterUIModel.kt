package com.ugurbuga.followtvmovie.domain.populartvshow.model

data class PosterUIModel(
    val posterList: MutableList<Any>,
    val page: Int,
    val totalPages: Int,
)