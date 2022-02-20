package com.ugurbuga.followtvmovie.domain.moviedetail.image


import com.squareup.moshi.Json

data class ImageResponse(
    @Json(name = "backdrops")
    val backdrops: List<PosterResponse>,
    @Json(name = "id")
    val id: Int,
    @Json(name = "logos")
    val logos: List<PosterResponse>,
    @Json(name = "posters")
    val posters: List<PosterResponse>
)