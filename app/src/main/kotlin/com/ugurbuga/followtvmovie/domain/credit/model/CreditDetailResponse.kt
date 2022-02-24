package com.ugurbuga.followtvmovie.domain.credit.model


import com.squareup.moshi.Json

data class CreditDetailResponse(
    @Json(name = "credit_type")
    val creditType: String,
    @Json(name = "department")
    val department: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "job")
    val job: String,
    @Json(name = "media")
    val media: MediaResponse,
    @Json(name = "media_type")
    val mediaType: String,
    @Json(name = "person")
    val person: PersonResponse
)