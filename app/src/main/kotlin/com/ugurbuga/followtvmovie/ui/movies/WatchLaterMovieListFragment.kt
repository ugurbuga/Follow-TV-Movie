package com.ugurbuga.followtvmovie.ui.movies

import android.os.Bundle
import com.ugurbuga.followtvmovie.ui.discover.MediaType
import com.ugurbuga.followtvmovie.ui.favorite.FavoriteListFragment
import com.ugurbuga.followtvmovie.ui.favorite.FavoriteListType
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