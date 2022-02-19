package com.ugurbuga.followtvmovie.ui.discover

import androidx.appcompat.widget.AppCompatImageView
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.ugurbuga.followtvmovie.DiscoverNavGraphDirections
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVMFragment
import com.ugurbuga.followtvmovie.databinding.FragmentDiscoverBinding
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import com.ugurbuga.followtvmovie.extensions.collect
import com.ugurbuga.followtvmovie.extensions.scrollListener
import com.ugurbuga.followtvmovie.ui.discover.popularlist.adapter.PosterAdapter
import com.ugurbuga.followtvmovie.ui.discover.popularlist.adapter.PosterViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiscoverFragment : FTMBaseVMFragment<DiscoverViewModel, FragmentDiscoverBinding>() {

    override fun getResourceLayoutId() = R.layout.fragment_discover

    override fun onInitDataBinding() {
        collect(viewModel.discover, ::onDiscover)

        with(viewBinding) {
            popularMovieRecyclerView.adapter = PosterAdapter(::onPopularMovieClicked)
            popularTvShowRecyclerView.adapter = PosterAdapter(::onPopularTvShowClicked)
            upcomingMovieRecyclerView.adapter = PosterAdapter(::onUpcomingMovieClicked)

            popularMovieRecyclerView.apply {
                scrollListener(::onPopularMovieScroll)
            }

            popularTvShowRecyclerView.apply {
                scrollListener(::onPopularTvShowScroll)
            }

            upcomingMovieRecyclerView.apply {
                scrollListener(::onUpcomingMovieScroll)
            }
        }
    }

    //////////////////////////////////////////////////////

    private fun onDiscover(posterViewState: PosterViewState) {
        viewBinding.viewState = posterViewState
    }

    //////////////////////////////////////////////////////

    private fun onPopularMovieScroll(
        visibleItemCount: Int,
        firstVisibleItemPosition: Int,
        totalItemCount: Int
    ) {
        viewModel.getNewItemsPopularMovie(
            visibleItemCount,
            firstVisibleItemPosition,
            totalItemCount
        )
    }

    private fun onPopularMovieClicked(
        poster: PosterItemUIModel,
        imageView: AppCompatImageView
    ) {
        val extras = FragmentNavigatorExtras(imageView to getString(R.string.image_big))
        val directions =
            DiscoverNavGraphDirections.actionToMovieDetailFragment(poster.id, poster.posterPath)
        findNavController().navigate(directions, extras)
    }

//////////////////////////////////////////////////////

    private fun onPopularTvShowScroll(
        visibleItemCount: Int,
        firstVisibleItemPosition: Int,
        totalItemCount: Int
    ) {
        viewModel.getNewItemsPopularTvShow(
            visibleItemCount,
            firstVisibleItemPosition,
            totalItemCount
        )
    }

    private fun onPopularTvShowClicked(
        poster: PosterItemUIModel,
        imageView: AppCompatImageView
    ) {
        val extras = FragmentNavigatorExtras(imageView to getString(R.string.image_big))
        val directions =
            DiscoverNavGraphDirections.actionToMovieDetailFragment(poster.id, poster.posterPath)
        findNavController().navigate(directions, extras)
    }

//////////////////////////////////////////////////////

    private fun onUpcomingMovieScroll(
        visibleItemCount: Int,
        firstVisibleItemPosition: Int,
        totalItemCount: Int
    ) {
        viewModel.getNewItemsUpcomingMovie(
            visibleItemCount,
            firstVisibleItemPosition,
            totalItemCount
        )
    }

    private fun onUpcomingMovieClicked(
        poster: PosterItemUIModel,
        imageView: AppCompatImageView
    ) {
        val extras = FragmentNavigatorExtras(imageView to getString(R.string.image_big))
        val directions =
            DiscoverNavGraphDirections.actionToMovieDetailFragment(poster.id, poster.posterPath)
        findNavController().navigate(directions, extras)
    }

}