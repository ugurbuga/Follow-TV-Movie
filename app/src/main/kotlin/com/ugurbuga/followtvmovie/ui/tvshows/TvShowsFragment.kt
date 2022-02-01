package com.ugurbuga.followtvmovie.ui.tvshows

import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVMFragment
import com.ugurbuga.followtvmovie.databinding.FragmentTvShowsBinding
import com.ugurbuga.followtvmovie.view.toolbar.ToolbarViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowsFragment : FTMBaseVMFragment<TvShowsViewModel, FragmentTvShowsBinding>() {

    override fun getToolbarViewState() = ToolbarViewState.NoToolbar

    override fun getResourceLayoutId() = R.layout.fragment_tv_shows

    override fun getViewModel() = TvShowsViewModel::class.java

    override fun onInitDataBinding() {

    }

}