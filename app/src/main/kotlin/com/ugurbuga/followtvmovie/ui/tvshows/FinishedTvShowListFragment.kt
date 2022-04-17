package com.ugurbuga.followtvmovie.ui.tvshows

import android.os.Bundle
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import com.ugurbuga.followtvmovie.extensions.collect
import com.ugurbuga.followtvmovie.ui.discover.MediaType
import com.ugurbuga.followtvmovie.ui.favorite.FavoriteListFragment
import com.ugurbuga.followtvmovie.ui.favorite.FavoriteListType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FinishedTvShowListFragment : FavoriteListFragment() {

    private val sharedViewModel: TvShowsViewModel by viewModels({ requireParentFragment() })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setFavoriteListType(MediaType.TV, FavoriteListType.WATCHED_LIST)
    }

    override fun onInitDataBinding() {
        super.onInitDataBinding()
        collect(sharedViewModel.query, ::onQuery)
    }

    override fun onPosterItemClick(poster: PosterItemUIModel, imageView: AppCompatImageView) {
        val extras = FragmentNavigatorExtras(imageView to getString(R.string.image_big))
        val directions =
            TvShowsFragmentDirections.actionTvShowsToTvShowDetail(
                poster.id,
                poster.posterPath
            )
        navigate(directions, extras)
    }

    private fun onQuery(query: String) {
        viewModel.setQuery(query)
    }

    companion object {
        fun newInstance() = FinishedTvShowListFragment()
    }
}