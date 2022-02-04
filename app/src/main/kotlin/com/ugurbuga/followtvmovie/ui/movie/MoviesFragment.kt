package com.ugurbuga.followtvmovie.ui.movie

import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.GridLayoutManager
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVMFragment
import com.ugurbuga.followtvmovie.common.Util
import com.ugurbuga.followtvmovie.databinding.FragmentMovieBinding
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import com.ugurbuga.followtvmovie.extensions.observe
import com.ugurbuga.followtvmovie.ui.discover.popularlist.adapter.PosterAdapter
import com.ugurbuga.followtvmovie.ui.discover.popularlist.adapter.PosterHolderType
import com.ugurbuga.followtvmovie.ui.discover.popularlist.adapter.PosterItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : FTMBaseVMFragment<MoviesViewModel, FragmentMovieBinding>() {

    override fun getResourceLayoutId() = R.layout.fragment_movie

    private val posterAdapter: PosterAdapter by lazy {
        PosterAdapter(
            requireContext(),
            ::onPosterItemClick
        )
    }

    override fun onInitDataBinding() {
        observe(viewModel.posterList, ::onPosterList)

        val gridLayoutManager = GridLayoutManager(context, 2)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (posterAdapter.getItemViewType(position)) {
                    PosterHolderType.POSTER -> 1
                    PosterHolderType.LOADING -> 2
                    else -> Util.INVALID_INDEX
                }
            }
        }

        viewBinding.movieListRecyclerView.apply {
            adapter = posterAdapter
            layoutManager = gridLayoutManager
            addItemDecoration(PosterItemDecoration())
        }

    }

    private fun onPosterList(posterList: MutableList<PosterItemUIModel>) {
        posterAdapter.submitList((posterList as MutableList<*>).toMutableList())
    }

    private fun onPosterItemClick(poster: PosterItemUIModel, imageView: AppCompatImageView) {
        //TODO: Navigate Detail
    }
}