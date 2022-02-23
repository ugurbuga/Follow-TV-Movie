package com.ugurbuga.followtvmovie.domain.moviedetail.external


import com.squareup.moshi.Json

data class ExternalIdsResponse(
    @Json(name = "facebook_id")
    val facebookId: String?,
    @Json(name = "id")
    val id: Int,
    @Json(name = "imdb_id")
    val imdbId: String?,
    @Json(name = "instagram_id")
    val instagramId: String?,
    @Json(name = "twitter_id")
    val twitterId: String?
)