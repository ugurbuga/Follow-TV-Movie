package com.ugurbuga.followtvmovie.watch.detail

data class MovieDetailUIModel(
    val adult: Boolean,
    val genres: List<GenreUIModel>,
    val id: String,
    val overview: String?,
    val posterPath: String,
    val releaseDate: String,
    val releaseDateLong: Long,
    val status: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
) {
    fun getProgressValue(): Int {
        return (voteAverage.times(10.0)).toInt()
    }
}