package com.ugurbuga.followtvmovie.data.model.response.tvshowdetail


import com.squareup.moshi.Json

data class NetworkResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "logo_path")
    val logoPath: String?,
    @Json(name = "name")
    val name: String,
    @Json(name = "origin_country")
    val originCountry: String
)