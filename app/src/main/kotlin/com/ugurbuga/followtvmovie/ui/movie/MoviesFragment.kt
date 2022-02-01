package com.ugurbuga.followtvmovie.ui.movie

import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVMFragment
import com.ugurbuga.followtvmovie.databinding.FragmentMovieBinding
import com.ugurbuga.followtvmovie.view.toolbar.ToolbarViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : FTMBaseVMFragment<MoviesViewModel, FragmentMovieBinding>() {

    override fun getToolbarViewState() = ToolbarViewState.NoToolbar

    override fun getResourceLayoutId() = R.layout.fragment_movie

    override fun getViewModel() = MoviesViewModel::class.java

    override fun onInitDataBinding() {

    }

}