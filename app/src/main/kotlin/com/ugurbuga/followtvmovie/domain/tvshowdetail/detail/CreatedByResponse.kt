package com.ugurbuga.followtvmovie.domain.tvshowdetail.detail


import com.squareup.moshi.Json

data class CreatedByResponse(
    @Json(name = "credit_id")
    val creditId: String,
    @Json(name = "gender")
    val gender: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "profile_path")
    val profilePath: String
)