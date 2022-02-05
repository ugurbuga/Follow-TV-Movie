package com.ugurbuga.followtvmovie.ui.discover.popularlist.adapter

import com.ugurbuga.followtvmovie.base.adapter.ListAdapterItem

data class PosterViewState(
    val popularMovieList: MutableList<ListAdapterItem> = arrayListOf(),
    val popularTvShowList: MutableList<ListAdapterItem> = arrayListOf(),
    val upcomingMovieList: MutableList<ListAdapterItem> = arrayListOf()
)