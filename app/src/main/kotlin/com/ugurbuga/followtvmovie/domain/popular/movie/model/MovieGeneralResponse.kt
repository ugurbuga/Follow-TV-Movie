package com.ugurbuga.followtvmovie.domain.popular.movie.model

import com.squareup.moshi.Json

data class MovieGeneralResponse(
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val results: List<MovieResponse>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)