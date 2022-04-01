package com.ugurbuga.followtvmovie.watch.ui.discover

import androidx.annotation.StringDef

@StringDef(
    ScreenType.POPULAR_MOVIES,
    ScreenType.COMING_SOON_MOVIES
)
annotation class ScreenType {
    companion object {
        const val POPULAR_MOVIES = "POPULAR_MOVIES"
        const val COMING_SOON_MOVIES = "COMING_SOON_MOVIES"
    }
}