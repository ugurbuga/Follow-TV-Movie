package com.ugurbuga.followtvmovie.ui.favorite

import androidx.appcompat.widget.AppCompatImageView
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.GridLayoutManager
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVMFragment
import com.ugurbuga.followtvmovie.databinding.FragmentFavoriteListBinding
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import com.ugurbuga.followtvmovie.extensions.collect
import com.ugurbuga.followtvmovie.ui.discover.adapter.PosterHolderType
import com.ugurbuga.followtvmovie.ui.movies.MoviesFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class FavoriteListFragment : FTMBaseVMFragment<FavoriteListViewModel, FragmentFavoriteListBinding>() {

    override fun getResourceLayoutId() = R.layout.fragment_favorite_list

    override fun viewModelClass() = FavoriteListViewModel::class.java

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
            favoriteListRecyclerView.apply {
                layoutManager = gridLayoutManager
                adapter = favoriteAdapter
            }
        }

    }

    private fun onFavoriteViewState(viewState: FavoriteViewState) {
        viewBinding.viewState = viewState
    }

    private fun onPosterItemClick(poster: PosterItemUIModel, imageView: AppCompatImageView) {
        val extras = FragmentNavigatorExtras(imageView to getString(R.string.image_big))
        val directions =
            MoviesFragmentDirections.actionMoviesToMovieDetail(poster.id, poster.posterPath)
        navigate(directions, extras)
    }
}