package com.ugurbuga.followtvmovie.data.model.response.review


import com.squareup.moshi.Json

data class ReviewResponse(
    @Json(name = "author")
    val author: String,
    @Json(name = "author_details")
    val authorDetails: AuthorDetailsResponse,
    @Json(name = "content")
    val content: String,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "updated_at")
    val updatedAt: String,
    @Json(name = "url")
    val url: String
)