package com.ugurbuga.followtvmovie.ui.discover

import androidx.appcompat.widget.AppCompatImageView
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVMFragment
import com.ugurbuga.followtvmovie.databinding.FragmentDiscoverBinding
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import com.ugurbuga.followtvmovie.core.extensions.collect
import com.ugurbuga.followtvmovie.core.extensions.scrollEndListener
import com.ugurbuga.followtvmovie.ui.discover.adapter.PosterAdapter
import com.ugurbuga.followtvmovie.ui.discover.adapter.PosterViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiscoverFragment : FTMBaseVMFragment<DiscoverViewModel, FragmentDiscoverBinding>() {

    override fun getResourceLayoutId() = R.layout.fragment_discover

    override fun viewModelClass() = DiscoverViewModel::class.java

    override fun onInitDataBinding() {
        collect(viewModel.discover, ::onDiscover)

        with(viewBinding) {
            popularMovieRecyclerView.adapter = PosterAdapter(::onPopularMovieClicked)
            popularTvShowRecyclerView.adapter = PosterAdapter(::onPopularTvShowClicked)
            upcomingMovieRecyclerView.adapter = PosterAdapter(::onUpcomingMovieClicked)

            popularMovieRecyclerView.scrollEndListener {
                viewModel.getNewItemsPopularMovie()
            }

            popularTvShowRecyclerView.scrollEndListener {
                viewModel.getNewItemsPopularTvShow()
            }

            upcomingMovieRecyclerView.scrollEndListener {
                viewModel.getNewItemsUpcomingMovie()
            }

            toolbar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.search -> {
                        navigate(DiscoverFragmentDirections.actionDiscoverToSearch())
                    }
                    R.id.settings -> {
                        navigate(DiscoverFragmentDirections.actionDiscoverToSettings())
                    }
                }
                true
            }
        }
    }

    //////////////////////////////////////////////////////

    private fun onDiscover(posterViewState: PosterViewState) {
        viewBinding.viewState = posterViewState
    }

    //////////////////////////////////////////////////////

    private fun onPopularMovieClicked(
        poster: PosterItemUIModel, imageView: AppCompatImageView
    ) {
        val extras = FragmentNavigatorExtras(imageView to getString(R.string.image_big))
        val directions =
            DiscoverFragmentDirections.actionDiscoverToMovieDetail(poster.id, poster.posterPath)
        navigate(directions, extras)
    }

    //////////////////////////////////////////////////////

    private fun onPopularTvShowClicked(
        poster: PosterItemUIModel, imageView: AppCompatImageView
    ) {
        val extras = FragmentNavigatorExtras(imageView to getString(R.string.image_big))
        val directions =
            DiscoverFragmentDirections.actionDiscoverToTvShowDetail(poster.id, poster.posterPath)
        navigate(directions, extras)
    }

    //////////////////////////////////////////////////////

    private fun onUpcomingMovieClicked(
        poster: PosterItemUIModel, imageView: AppCompatImageView
    ) {
        val extras = FragmentNavigatorExtras(imageView to getString(R.string.image_big))
        val directions =
            DiscoverFragmentDirections.actionDiscoverToMovieDetail(poster.id, poster.posterPath)
        navigate(directions, extras)
    }
}