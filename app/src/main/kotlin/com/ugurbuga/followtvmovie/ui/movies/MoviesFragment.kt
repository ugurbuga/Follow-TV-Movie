package com.ugurbuga.followtvmovie.ui.movies

import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVMFragment
import com.ugurbuga.followtvmovie.databinding.FragmentMoviesBinding
import com.ugurbuga.followtvmovie.ui.favorite.FavoriteFragmentAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : FTMBaseVMFragment<MoviesViewModel, FragmentMoviesBinding>() {

    override fun getResourceLayoutId() = R.layout.fragment_movies

    override fun generateViewModel() = MoviesViewModel::class.java

    override fun onInitDataBinding() {
        with(viewBinding) {
            val adapter =
                FavoriteFragmentAdapter(
                    requireContext(),
                    childFragmentManager
                )
            viewPager.adapter = adapter
        }
    }
}