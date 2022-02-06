package com.ugurbuga.followtvmovie.ui.discover

import com.ugurbuga.followtvmovie.ui.discover.popularlist.adapter.PosterAdapter

data class DiscoverAdapters(
    val popularMovieAdapter: PosterAdapter,
    val popularTvShowAdapter: PosterAdapter,
    val upcomingMovieAdapter: PosterAdapter,
)