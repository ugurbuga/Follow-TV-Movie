package com.ugurbuga.followtvmovie.domain.moviedetail.model.review


import com.squareup.moshi.Json

data class AuthorDetailsResponse(
    @Json(name = "avatar_path")
    val avatarPath: String?,
    @Json(name = "name")
    val name: String,
    @Json(name = "rating")
    val rating: Int?,
    @Json(name = "username")
    val username: String
)