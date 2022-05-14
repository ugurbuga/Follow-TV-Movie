package com.ugurbuga.followtvmovie.domain.seasondetail.model

import com.ugurbuga.followtvmovie.core.adapter.ListAdapterItem

data class EpisodeUIModel(
    val airDate: String,
    val crew: List<CrewUIModel>,
    val episodeNumber: Int,
    val guestStars: List<GuestStarUIModel>,
    val id: Int,
    val name: String,
    val overview: String,
    val productionCode: String,
    val seasonNumber: Int,
    val stillPath: String?,
    val voteAverage: Double,
    val voteCount: Int
) : ListAdapterItem