package com.ugurbuga.followtvmovie.domain.tvshowdetail.model

import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.GenreUIModel

data class TvShowDetailUIModel(
    val adult: Boolean,
    val genres: List<GenreUIModel>,
    val id: String,
    val overview: String?,
    val posterPath: String,
    val releaseDate: String,
    val releaseDateLong: Long,
    val status: String,
    val title: String,
    val voteAverage: Double,
    val seasons: List<SeasonUIModel>
) {
    fun getProgressValue(): Int {
        return (voteAverage.times(10.0)).toInt()
    }
}