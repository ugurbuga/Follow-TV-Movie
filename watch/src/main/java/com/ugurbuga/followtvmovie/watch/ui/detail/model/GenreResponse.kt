package com.ugurbuga.followtvmovie.watch.ui.detail.model


import com.squareup.moshi.Json

data class GenreResponse(

    @Json(name = "id")
    val id: String,

    @Json(name = "name")
    val name: String
)