package com.ugurbuga.followtvmovie.ui.discover

import androidx.annotation.StringDef

@StringDef(
    MediaType.MOVIE,
    MediaType.TV,
    MediaType.PERSON
)
annotation class MediaType {
    companion object {
        const val MOVIE = "movie"
        const val TV = "tv"
        const val PERSON = "person"
    }
}