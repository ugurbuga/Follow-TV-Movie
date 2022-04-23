package com.ugurbuga.followtvmovie.domain.moviedetail.model.video


import com.squareup.moshi.Json

data class VideosResponse(
    @Json(name = "id")
    val id: String,
    @Json(name = "results")
    val results: List<VideoResponse>
)