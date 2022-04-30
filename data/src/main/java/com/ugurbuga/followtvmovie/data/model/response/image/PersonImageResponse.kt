package com.ugurbuga.followtvmovie.data.model.response.image


import com.squareup.moshi.Json

data class PersonImageResponse(
    @Json(name = "id")
    val id: String,
    @Json(name = "profiles")
    val profiles: List<ImagePosterResponse>
)