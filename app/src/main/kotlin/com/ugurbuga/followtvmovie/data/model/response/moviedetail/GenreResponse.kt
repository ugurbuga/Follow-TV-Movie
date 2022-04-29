package com.ugurbuga.followtvmovie.data.model.response.moviedetail


import com.squareup.moshi.Json

data class GenreResponse(

    @Json(name = "id")
    val id: String,

    @Json(name = "name")
    val name: String
)