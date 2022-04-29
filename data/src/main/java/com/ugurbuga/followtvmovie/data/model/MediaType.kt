package com.ugurbuga.followtvmovie.data.model

import androidx.annotation.StringDef
//FIXME: Düzenlencek
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