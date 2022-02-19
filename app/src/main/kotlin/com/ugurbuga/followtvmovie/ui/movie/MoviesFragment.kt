package com.ugurbuga.followtvmovie.ui.movie

import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.GridLayoutManager
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVMFragment
import com.ugurbuga.followtvmovie.databinding.FragmentMovieBinding
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import com.ugurbuga.followtvmovie.extensions.collect
import com.ugurbuga.followtvmovie.ui.discover.popularlist.adapter.PosterHolderType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : FTMBaseVMFragment<MoviesViewModel, FragmentMovieBinding>() {

    override fun getResourceLayoutId() = R.layout.fragment_movie

    override fun onInitDataBinding() {
        collect(viewModel.favoriteViewState, ::onFavoriteViewState)

        with(viewBinding) {
            val favoriteAdapter = FavoriteAdapter(requireContext(), ::onPosterItemClick)
            val gridLayoutManager = GridLayoutManager(requireContext(), 2)
            gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (favoriteAdapter.getItemViewType(position)) {
                        PosterHolderType.POSTER -> 1
                        PosterHolderType.EMPTY -> 2
                        else -> 2
                    }
                }
            }
            movieListRecyclerView.apply {
                layoutManager = gridLayoutManager
                adapter = favoriteAdapter
            }
        }

    }

    private fun onFavoriteViewState(viewState: FavoriteViewState) {
        viewBinding.viewState = viewState
    }

    private fun onPosterItemClick(poster: PosterItemUIModel, imageView: AppCompatImageView) {
    }
}