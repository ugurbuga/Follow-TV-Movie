package com.ugurbuga.followtvmovie.ui.movie

import android.os.Bundle
import com.ugurbuga.followtvmovie.ui.discover.MediaType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WatchLaterMovieListFragment : FavoriteListFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setFavoriteListType(MediaType.MOVIE, FavoriteListType.WATCH_LATER_LIST)
    }

    override fun onInitDataBinding() {
        super.onInitDataBinding()
    }

    companion object {
        fun newInstance() = WatchLaterMovieListFragment()
    }
}