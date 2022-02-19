package com.ugurbuga.followtvmovie.ui.discover

import androidx.appcompat.widget.AppCompatImageView
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.ugurbuga.followtvmovie.DiscoverNavGraphDirections
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVMFragment
import com.ugurbuga.followtvmovie.base.adapter.ListAdapterItem
import com.ugurbuga.followtvmovie.databinding.FragmentDiscoverBinding
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import com.ugurbuga.followtvmovie.extensions.collect
import com.ugurbuga.followtvmovie.extensions.scrollListener
import com.ugurbuga.followtvmovie.extensions.updateList
import com.ugurbuga.followtvmovie.ui.discover.popularlist.adapter.PosterAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiscoverFragment : FTMBaseVMFragment<DiscoverViewModel, FragmentDiscoverBinding>() {

    override fun getResourceLayoutId() = R.layout.fragment_discover

    val popularMovieAdapter by lazy { PosterAdapter(::onPopularMovieClicked) }
    val popularTvShowAdapter by lazy { PosterAdapter(::onPopularTvShowClicked) }
    val upcomingMovieAdapter by lazy { PosterAdapter(::onUpcomingMovieClicked) }

    override fun onInitDataBinding() {
        //observe(viewModel.discover, ::onDiscover)
        collect(viewModel.popularMovies, ::onPopularMovies)
        collect(viewModel.upcomingMovies, ::onUpcomingMovies)
        collect(viewModel.popularTvShows, ::onPopularTvShows)

        with(viewBinding) {
            popularMovieRecyclerView.adapter = popularMovieAdapter
            popularTvShowRecyclerView.adapter = popularTvShowAdapter
            upcomingMovieRecyclerView.adapter = upcomingMovieAdapter

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

    private fun onPopularTvShows(listAdapterItems: MutableList<ListAdapterItem>) {
        popularTvShowAdapter.updateList(listAdapterItems)
    }

    private fun onUpcomingMovies(listAdapterItems: MutableList<ListAdapterItem>) {
        upcomingMovieAdapter.updateList(listAdapterItems)
    }

    private fun onPopularMovies(listAdapterItems: MutableList<ListAdapterItem>) {
        popularMovieAdapter.updateList(listAdapterItems)
    }

    //////////////////////////////////////////////////////

    private fun onPopularMovieScroll(
        visibleItemCount: Int, firstVisibleItemPosition: Int, totalItemCount: Int
    ) {
        viewModel.getNewItemsPopularMovie(
            visibleItemCount, firstVisibleItemPosition, totalItemCount
        )
    }

    private fun onPopularMovieClicked(
        poster: PosterItemUIModel, imageView: AppCompatImageView
    ) {
        val extras = FragmentNavigatorExtras(imageView to getString(R.string.image_big))
        val directions =
            DiscoverNavGraphDirections.actionToMovieDetailFragment(poster.id, poster.posterPath)
        findNavController().navigate(directions, extras)
    }

//////////////////////////////////////////////////////

    private fun onPopularTvShowScroll(
        visibleItemCount: Int, firstVisibleItemPosition: Int, totalItemCount: Int
    ) {
        viewModel.getNewItemsPopularTvShow(
            visibleItemCount, firstVisibleItemPosition, totalItemCount
        )
    }

    private fun onPopularTvShowClicked(
        poster: PosterItemUIModel, imageView: AppCompatImageView
    ) {
        val extras = FragmentNavigatorExtras(imageView to getString(R.string.image_big))
        val directions =
            DiscoverNavGraphDirections.actionToMovieDetailFragment(poster.id, poster.posterPath)
        findNavController().navigate(directions, extras)
    }

//////////////////////////////////////////////////////

    private fun onUpcomingMovieScroll(
        visibleItemCount: Int, firstVisibleItemPosition: Int, totalItemCount: Int
    ) {
        viewModel.getNewItemsUpcomingMovie(
            visibleItemCount, firstVisibleItemPosition, totalItemCount
        )
    }

    private fun onUpcomingMovieClicked(
        poster: PosterItemUIModel, imageView: AppCompatImageView
    ) {
        val extras = FragmentNavigatorExtras(imageView to getString(R.string.image_big))
        val directions =
            DiscoverNavGraphDirections.actionToMovieDetailFragment(poster.id, poster.posterPath)
        findNavController().navigate(directions, extras)
    }

}