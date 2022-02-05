package com.ugurbuga.followtvmovie.domain.moviedetail.model


data class MovieDetailUIModel(
    val adult: Boolean,
    val genres: List<GenreUIModel>,
    val id: Int,
    val overview: String?,
    val posterPath: String,
    val releaseDate: String,
    val status: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
)