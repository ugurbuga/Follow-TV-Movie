package com.ugurbuga.followtvmovie.ui.discover.popularlist

import androidx.annotation.StringDef

@StringDef(
    PopularListType.MOVIE,
    PopularListType.TV_SHOW
)
annotation class PopularListType {
    companion object {
        const val MOVIE = "movie"
        const val TV_SHOW = "tv_show"
    }
}