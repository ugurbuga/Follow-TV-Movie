package com.ugurbuga.followtvmovie.ui.movie

import androidx.annotation.StringDef

@StringDef(
    value = [
        FavoriteListType.WATCHED_LIST,
        FavoriteListType.WATCH_LATER_LIST
    ]
)
annotation class FavoriteListType {
    companion object {
        const val WATCHED_LIST = "watchedList"
        const val WATCH_LATER_LIST = "watchLaterList"
    }
}