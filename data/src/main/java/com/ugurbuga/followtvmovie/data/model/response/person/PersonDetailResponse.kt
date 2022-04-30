package com.ugurbuga.followtvmovie.data.model.response.person


import com.squareup.moshi.Json

data class PersonDetailResponse(
    @Json(name = "adult")
    val adult: Boolean,
    @Json(name = "also_known_as")
    val alsoKnownAs: List<String>,
    @Json(name = "biography")
    val biography: String,
    @Json(name = "birthday")
    val birthDay: String?,
    @Json(name = "deathday")
    val deathDay: String?,
    @Json(name = "gender")
    val gender: Int,
    @Json(name = "homepage")
    val homepage: String?,
    @Json(name = "id")
    val id: String,
    @Json(name = "imdb_id")
    val imdbId: String?,
    @Json(name = "known_for_department")
    val knownForDepartment: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "place_of_birth")
    val placeOfBirth: String?,
    @Json(name = "popularity")
    val popularity: Double,
    @Json(name = "profile_path")
    val profilePath: String?
)