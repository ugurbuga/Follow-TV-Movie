package com.ugurbuga.followtvmovie.domain.seasondetail.model


data class GuestStarUIModel(
    val adult: Boolean,
    val character: String,
    val creditId: String,
    val gender: Int,
    val id: Int,
    val knownForDepartment: String,
    val name: String,
    val order: Int,
    val originalName: String,
    val popularity: Double,
    val profilePath: String?
)