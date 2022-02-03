package com.ugurbuga.followtvmovie.ui.movie

import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVMFragment
import com.ugurbuga.followtvmovie.databinding.FragmentMovieBinding
import com.ugurbuga.followtvmovie.view.toolbar.FTMToolbar
import com.ugurbuga.followtvmovie.view.toolbar.ToolbarViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : FTMBaseVMFragment<MoviesViewModel, FragmentMovieBinding>() {

    override fun getToolbarViewState() =
        ToolbarViewState.ToolbarProperties(
            FTMToolbar.NavigationIconType.BACK_BUTTON,
            getString(R.string.movie)
        )

    override fun getResourceLayoutId() = R.layout.fragment_movie

    override fun onInitDataBinding() {

    }

}