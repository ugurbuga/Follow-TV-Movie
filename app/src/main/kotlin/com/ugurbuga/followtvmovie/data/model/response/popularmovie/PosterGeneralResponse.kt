package com.ugurbuga.followtvmovie.data.model.response.popularmovie

import com.squareup.moshi.Json

data class PosterGeneralResponse(
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val results: List<PosterResponse>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)