package com.ugurbuga.followtvmovie.ui.discover.adapter

import com.ugurbuga.followtvmovie.core.adapter.ListAdapterItem

data class PosterViewState(
    val popularMovieList: MutableList<ListAdapterItem> = arrayListOf(),
    val popularTvShowList: MutableList<ListAdapterItem> = arrayListOf(),
    val upcomingMovieList: MutableList<ListAdapterItem> = arrayListOf()
)