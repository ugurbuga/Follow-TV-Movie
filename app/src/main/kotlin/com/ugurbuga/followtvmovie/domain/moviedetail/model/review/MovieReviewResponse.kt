package com.ugurbuga.followtvmovie.domain.moviedetail.model.review


import com.squareup.moshi.Json

data class MovieReviewResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val results: List<ReviewResponse>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)