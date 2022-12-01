package com.ugurbuga.followtvmovie.ui.soon.list

import androidx.appcompat.widget.AppCompatImageView
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVMFragment
import com.ugurbuga.followtvmovie.databinding.FragmentSoonMovieListBinding
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import com.ugurbuga.followtvmovie.core.extensions.collect
import com.ugurbuga.followtvmovie.ui.soon.SoonFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class SoonListFragment :
    FTMBaseVMFragment<SoonListViewModel, FragmentSoonMovieListBinding>() {

    override fun getResourceLayoutId() = R.layout.fragment_soon_movie_list

    override fun viewModelClass() = SoonListViewModel::class.java

    override fun onInitDataBinding() {
        collect(viewModel.soonMovieListViewState, ::onSoonMovieListViewState)

        with(viewBinding) {
            movieListRecyclerView.adapter = SoonAdapter(::onPosterItemClick)
        }
    }

    private fun onSoonMovieListViewState(viewState: SoonListViewState) {
        viewBinding.viewState = viewState
    }

    private fun onPosterItemClick(poster: PosterItemUIModel, imageView: AppCompatImageView) {
        val extras = FragmentNavigatorExtras(imageView to getString(R.string.image_big))
        val directions =
            SoonFragmentDirections.actionSoonToMovieDetail(poster.id, poster.posterPath)
        navigate(directions, extras)
    }

}