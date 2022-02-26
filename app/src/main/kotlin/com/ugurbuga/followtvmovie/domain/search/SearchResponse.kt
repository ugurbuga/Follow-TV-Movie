package com.ugurbuga.followtvmovie.domain.search


import com.squareup.moshi.Json

data class SearchResponse(
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val results: List<SearchItemResponse>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)