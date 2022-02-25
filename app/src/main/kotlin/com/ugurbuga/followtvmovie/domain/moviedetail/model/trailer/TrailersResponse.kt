package com.ugurbuga.followtvmovie.domain.moviedetail.model.trailer


import com.squareup.moshi.Json

data class TrailersResponse(
    @Json(name = "id")
    val id: String,
    @Json(name = "results")
    val results: List<TrailerResponse>
)