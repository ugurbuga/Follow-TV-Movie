package com.ugurbuga.followtvmovie.ui.movies

import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVMFragment
import com.ugurbuga.followtvmovie.databinding.FragmentMoviesBinding
import com.ugurbuga.followtvmovie.ui.favorite.FavoriteMoviesFragmentAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : FTMBaseVMFragment<MoviesViewModel, FragmentMoviesBinding>() {

    override fun getResourceLayoutId() = R.layout.fragment_movies

    override fun viewModelClass() = MoviesViewModel::class.java

    private val adapter: FavoriteMoviesFragmentAdapter by lazy {
        FavoriteMoviesFragmentAdapter(
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