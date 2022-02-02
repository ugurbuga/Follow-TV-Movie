package com.ugurbuga.followtvmovie.ui.discover.popular.tv

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVMFragment
import com.ugurbuga.followtvmovie.common.Util
import com.ugurbuga.followtvmovie.databinding.FragmentPopularTvShowBinding
import com.ugurbuga.followtvmovie.domain.populartvshow.model.PosterUIModel
import com.ugurbuga.followtvmovie.extensions.observe
import com.ugurbuga.followtvmovie.ui.discover.popular.PosterAdapter
import com.ugurbuga.followtvmovie.view.toolbar.ToolbarViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularTvShowFragment :
    FTMBaseVMFragment<PopularTvShowViewModel, FragmentPopularTvShowBinding>() {

    override fun getResourceLayoutId() = R.layout.fragment_popular_tv_show

    override fun getViewModel() = PopularTvShowViewModel::class.java

    override fun getToolbarViewState() = ToolbarViewState.NoToolbar

    private val posterAdapter: PosterAdapter by lazy {
        PosterAdapter(
            requireContext(),
            ::onPosterItemClick
        )
    }

    companion object {
        fun newInstance() = PopularTvShowFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onInitDataBinding() {
        observe(mViewModel.poster, ::onPoster)

        val gridLayoutManager = GridLayoutManager(context, 2)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (posterAdapter.getItemViewType(position)) {
                    PosterAdapter.POSTER -> 1
                    PosterAdapter.LOADING -> 2
                    else -> Util.INVALID_INDEX
                }
            }
        }

        viewBinding.popularTvShowRecyclerView.apply {
            adapter = posterAdapter
            layoutManager = gridLayoutManager

            addOnScrollListener(object :
                RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager: LinearLayoutManager =
                        this@apply.layoutManager as LinearLayoutManager

                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                    //TODO: Paging
                }
            })
        }

    }

    private fun onPoster(posterList: PosterUIModel) {
        posterAdapter.submitList(posterList.posterList)
    }

    private fun onPosterItemClick(id: Int) {
        //TODO: Navigate Detail
    }
}
