package com.ugurbuga.followtvmovie.domain.populartvshow.model


import com.squareup.moshi.Json

data class TvShowGeneralResponse(
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val results: List<TvShowResponse>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)