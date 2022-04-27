package com.ugurbuga.followtvmovie.data.model.response.seasondetail


import com.squareup.moshi.Json

data class SeasonDetailResponse(
    @Json(name = "air_date")
    val airDate: String,
    @Json(name = "episodes")
    val episodes: List<EpisodeResponse>,
    @Json(name = "_id")
    val _id: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "poster_path")
    val posterPath: String,
    @Json(name = "season_number")
    val seasonNumber: Int
)