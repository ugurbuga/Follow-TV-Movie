package com.ugurbuga.followtvmovie.ui.soon.movie

import android.os.Bundle
import com.ugurbuga.followtvmovie.ui.soon.list.SoonListFragment

class SoonMovieListFragment : SoonListFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getSoonMovies()
    }

    companion object {
        fun newInstance() = SoonMovieListFragment()
    }
}