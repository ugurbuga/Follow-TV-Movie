package com.ugurbuga.followtvmovie.watch.domain.detail.model


import com.squareup.moshi.Json

data class SpokenLanguageResponse(
    @Json(name = "english_name")
    val englishName: String,
    @Json(name = "iso_639_1")
    val iso6391: String,
    @Json(name = "name")
    val name: String
)