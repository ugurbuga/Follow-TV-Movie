package com.ugurbuga.followtvmovie.data.model.response.image


import com.squareup.moshi.Json

data class ImagePosterResponse(
    @Json(name = "aspect_ratio")
    val aspectRatio: Double,
    @Json(name = "file_path")
    val filePath: String,
    @Json(name = "height")
    val height: Int,
    @Json(name = "iso_639_1")
    val iso6391: String?,
    @Json(name = "vote_average")
    val voteAverage: Double,
    @Json(name = "vote_count")
    val voteCount: Int,
    @Json(name = "width")
    val width: Int
)