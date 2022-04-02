package com.ugurbuga.followtvmovie.watch.domain.detail.model


import com.squareup.moshi.Json

data class ProductionCompanyResponse(

    @Json(name = "id")
    val id: String,

    @Json(name = "logo_path")
    val logoPath: String?,

    @Json(name = "name")
    val name: String,

    @Json(name = "origin_country")
    val originCountry: String
)