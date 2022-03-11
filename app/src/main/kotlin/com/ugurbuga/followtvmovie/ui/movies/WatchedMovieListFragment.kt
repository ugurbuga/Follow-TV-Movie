package com.ugurbuga.followtvmovie.ui.movies

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.ugurbuga.followtvmovie.extensions.collect
import com.ugurbuga.followtvmovie.ui.discover.MediaType
import com.ugurbuga.followtvmovie.ui.favorite.FavoriteListFragment
import com.ugurbuga.followtvmovie.ui.favorite.FavoriteListType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WatchedMovieListFragment : FavoriteListFragment() {

    private val sharedViewModel: MoviesViewModel by viewModels({requireParentFragment()})

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setFavoriteListType(MediaType.MOVIE, FavoriteListType.WATCHED_LIST)
    }

    override fun onInitDataBinding() {
        super.onInitDataBinding()
        collect(sharedViewModel.query,::onQuery)
    }

    private fun onQuery(query: String) {
        viewModel.setQuery(query)
    }

    companion object {
        fun newInstance() = WatchedMovieListFragment()
    }
}