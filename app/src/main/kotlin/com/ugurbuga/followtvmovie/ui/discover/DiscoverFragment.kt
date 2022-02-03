package com.ugurbuga.followtvmovie.ui.discover

import com.google.android.material.tabs.TabLayoutMediator
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVMFragment
import com.ugurbuga.followtvmovie.common.Util
import com.ugurbuga.followtvmovie.databinding.FragmentDiscoverBinding
import com.ugurbuga.followtvmovie.view.toolbar.ToolbarViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiscoverFragment : FTMBaseVMFragment<DiscoverViewModel, FragmentDiscoverBinding>() {

    override fun getToolbarViewState() = ToolbarViewState.NoToolbar

    override fun getResourceLayoutId() = R.layout.fragment_discover

    override fun onInitDataBinding() {
        viewBinding.apply {
            val adapter = DiscoverFragmentAdapter(requireActivity())
            discoverViewPager.adapter = adapter

            TabLayoutMediator(discoverTabLayout, discoverViewPager) { tab, position ->
                tab.text = when (position) {

                    0 -> getString(R.string.movie)

                    1 -> getString(R.string.tv_series)

                    else -> Util.EMPTY_STRING
                }
            }.attach()
        }
    }

}