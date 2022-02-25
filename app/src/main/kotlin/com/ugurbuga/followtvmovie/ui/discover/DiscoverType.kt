package com.ugurbuga.followtvmovie.ui.discover

import androidx.annotation.StringDef

@StringDef(
    DiscoverType.MOVIE,
    DiscoverType.TV
)
annotation class DiscoverType {
    companion object {
        const val MOVIE = "movie"
        const val TV = "tv"
    }
}