package com.ugurbuga.followtvmovie.domain.moviedetail.model


import com.squareup.moshi.Json

data class GenreResponse(

    @Json(name = "id")
    val id: Int,

    @Json(name = "name")
    val name: String
)