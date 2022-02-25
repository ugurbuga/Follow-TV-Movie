package com.ugurbuga.followtvmovie.domain.popular.tvshow.model


import com.squareup.moshi.Json

data class TvShowResponse(
    @Json(name = "backdrop_path")
    val backdropPath: String?,

    @Json(name = "first_air_date")
    val firstAirDate: String,

    @Json(name = "genre_ids")
    val genreIds: List<Int>,

    @Json(name = "id")
    val id: String,

    @Json(name = "name")
    val name: String,

    @Json(name = "origin_country")
    val originCountry: List<String>,

    @Json(name = "original_language")
    val originalLanguage: String,

    @Json(name = "original_name")
    val originalName: String,

    @Json(name = "overview")
    val overview: String,

    @Json(name = "popularity")
    val popularity: Double,

    @Json(name = "poster_path")
    val posterPath: String?,

    @Json(name = "vote_average")
    val voteAverage: Double,

    @Json(name = "vote_count")
    val voteCount: Int
)