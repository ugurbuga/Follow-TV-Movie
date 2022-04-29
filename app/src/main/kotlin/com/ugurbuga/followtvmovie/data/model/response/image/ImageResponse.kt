package com.ugurbuga.followtvmovie.data.model.response.image


import com.squareup.moshi.Json

data class ImageResponse(
    @Json(name = "backdrops")
    val backdrops: List<ImagePosterResponse>,
    @Json(name = "id")
    val id: String,
    @Json(name = "logos")
    val logos: List<ImagePosterResponse>,
    @Json(name = "posters")
    val posters: List<ImagePosterResponse>
)