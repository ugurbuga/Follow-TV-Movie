package com.ugurbuga.followtvmovie.ui.tvshows

import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVMFragment
import com.ugurbuga.followtvmovie.databinding.FragmentTvShowsBinding
import com.ugurbuga.followtvmovie.ui.favorite.FavoriteTvShowsFragmentAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowsFragment : FTMBaseVMFragment<TvShowsViewModel, FragmentTvShowsBinding>() {

    override fun getResourceLayoutId() = R.layout.fragment_tv_shows

    override fun viewModelClass() = TvShowsViewModel::class.java

    private val adapter: FavoriteTvShowsFragmentAdapter by lazy {
        FavoriteTvShowsFragmentAdapter(
            requireContext(),
            childFragmentManager
        )
    }

    override fun onInitDataBinding() {
        with(viewBinding) {
            viewPager.adapter = adapter

            toolbar.setSearchView(
                menuSearchItemId = R.id.search,
                isExpand = false,
                onQueryChanged = ::onQueryChanged
            )
        }
    }

    private fun onQueryChanged(query: String) {
        viewModel.setQuery(query)
    }
}