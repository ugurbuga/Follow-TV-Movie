package com.ugurbuga.followtvmovie.ui.movies

import androidx.fragment.app.activityViewModels
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVMFragment
import com.ugurbuga.followtvmovie.databinding.FragmentMoviesBinding
import com.ugurbuga.followtvmovie.ui.favorite.FavoriteFragmentAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : FTMBaseVMFragment<MoviesViewModel, FragmentMoviesBinding>() {

    private val sharedViewModel: MoviesViewModel by activityViewModels()

    override fun getResourceLayoutId() = R.layout.fragment_movies

    override fun generateViewModel() = MoviesViewModel::class.java

    private val adapter: FavoriteFragmentAdapter by lazy {
        FavoriteFragmentAdapter(
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