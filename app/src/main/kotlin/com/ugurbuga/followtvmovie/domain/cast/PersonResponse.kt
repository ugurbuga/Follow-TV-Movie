package com.ugurbuga.followtvmovie.domain.cast


import com.squareup.moshi.Json

data class PersonResponse(
    @Json(name = "adult")
    val adult: Boolean,
    @Json(name = "gender")
    val gender: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "known_for")
    val knownFor: List<KnownForResponse>,
    @Json(name = "known_for_department")
    val knownForDepartment: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "popularity")
    val popularity: Double,
    @Json(name = "profile_path")
    val profilePath: String
)