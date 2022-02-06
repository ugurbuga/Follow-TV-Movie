package com.ugurbuga.followtvmovie.ui.movie

import androidx.appcompat.widget.AppCompatImageView
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVMFragment
import com.ugurbuga.followtvmovie.databinding.FragmentMovieBinding
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import com.ugurbuga.followtvmovie.extensions.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : FTMBaseVMFragment<MoviesViewModel, FragmentMovieBinding>() {

    override fun getResourceLayoutId() = R.layout.fragment_movie

    override fun onInitDataBinding() {
        observe(viewModel.favoriteViewState, ::onFavoriteViewState)

        with(viewBinding) {
            favoriteAdapter = FavoriteAdapter(requireContext(), ::onPosterItemClick)
        }

    }

    private fun onFavoriteViewState(viewState: FavoriteViewState) {
        viewBinding.viewState = viewState
    }

    private fun onPosterItemClick(poster: PosterItemUIModel, imageView: AppCompatImageView) {
    }
}