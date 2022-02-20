package com.ugurbuga.followtvmovie.ui.discover.adapter

import androidx.annotation.IntDef

@IntDef(
    PosterHolderType.POSTER,
    PosterHolderType.LOADING,
    PosterHolderType.EMPTY,
)
annotation class PosterHolderType {
    companion object {
        const val POSTER = 0
        const val LOADING = 1
        const val EMPTY = 2
    }
}