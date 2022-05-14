package com.ugurbuga.followtvmovie.domain.seasondetail.model


data class SeasonDetailUIModel(
    val airDate: String,
    val episodes: List<EpisodeUIModel>,
    val _id: String,
    val id: Int,
    val name: String,
    val overview: String,
    val posterPath: String,
    val seasonNumber: Int
)