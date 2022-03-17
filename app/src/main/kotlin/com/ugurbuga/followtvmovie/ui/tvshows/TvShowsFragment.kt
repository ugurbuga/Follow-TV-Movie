package com.ugurbuga.followtvmovie.ui.tvshows

import androidx.appcompat.widget.AppCompatImageView
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVMFragment
import com.ugurbuga.followtvmovie.databinding.FragmentTvShowsBinding
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import com.ugurbuga.followtvmovie.extensions.collect
import com.ugurbuga.followtvmovie.ui.favorite.FavoriteAdapter
import com.ugurbuga.followtvmovie.ui.favorite.FavoriteViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowsFragment : FTMBaseVMFragment<TvShowsViewModel, FragmentTvShowsBinding>() {

    override fun getResourceLayoutId() = R.layout.fragment_tv_shows

    override fun viewModelClass() = TvShowsViewModel::class.java

    override fun onInitDataBinding() {
        collect(viewModel.favoriteViewState, ::onFavoriteViewState)

        with(viewBinding) {
            tvShowListRecyclerView.adapter = FavoriteAdapter(requireContext(), ::onPosterItemClick)
        }
    }

    private fun onFavoriteViewState(viewState: FavoriteViewState) {
        viewBinding.viewState = viewState
    }

    private fun onPosterItemClick(poster: PosterItemUIModel, imageView: AppCompatImageView) {
    }
}