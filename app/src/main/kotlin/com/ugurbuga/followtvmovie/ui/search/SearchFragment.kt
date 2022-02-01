package com.ugurbuga.followtvmovie.ui.search

import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVMFragment
import com.ugurbuga.followtvmovie.databinding.FragmentSearchBinding
import com.ugurbuga.followtvmovie.view.toolbar.ToolbarViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : FTMBaseVMFragment<SearchViewModel, FragmentSearchBinding>() {

    override fun getToolbarViewState() = ToolbarViewState.NoToolbar

    override fun getResourceLayoutId() = R.layout.fragment_search

    override fun getViewModel() = SearchViewModel::class.java

    override fun onInitDataBinding() {

    }

}