package com.ugurbuga.followtvmovie.domain.tvshowdetail.model

import com.ugurbuga.followtvmovie.core.adapter.ListAdapterItem

data class SeasonUIModel(
    val airDate: String?,
    val episodeCount: Int,
    val id: Int,
    val name: String,
    val overview: String,
    val posterPath: String?,
    val seasonNumber: Int
) : ListAdapterItem