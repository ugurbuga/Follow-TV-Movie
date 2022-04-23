package com.ugurbuga.followtvmovie.domain.popular.movie.model

import com.squareup.moshi.Json

data class PosterResponse(
    @Json(name = "id")
    val id: String,

    @Json(name = "title")
    val title: String?,

    @Json(name = "name")
    val name: String?,

    @Json(name = "poster_path")
    val posterPath: String?,

    @Json(name = "backdrop_path")
    val backdropPath: String?,

    @Json(name = "release_date")
    val releaseDate: String?,

    @Json(name = "first_air_date")
    val firstAirDate: String?,
)