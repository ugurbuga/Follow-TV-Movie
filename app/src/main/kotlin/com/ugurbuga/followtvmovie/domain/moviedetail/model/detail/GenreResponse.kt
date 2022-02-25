package com.ugurbuga.followtvmovie.domain.moviedetail.model.detail


import com.squareup.moshi.Json

data class GenreResponse(

    @Json(name = "id")
    val id: String,

    @Json(name = "name")
    val name: String
)