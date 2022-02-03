package com.ugurbuga.followtvmovie.ui.soon

import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVMFragment
import com.ugurbuga.followtvmovie.databinding.FragmentSoonBinding
import com.ugurbuga.followtvmovie.view.toolbar.FTMToolbar
import com.ugurbuga.followtvmovie.view.toolbar.ToolbarViewState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SoonFragment : FTMBaseVMFragment<SoonViewModel, FragmentSoonBinding>() {

    override fun getToolbarViewState() =
        ToolbarViewState.ToolbarProperties(
            FTMToolbar.NavigationIconType.LOGO,
            getString(R.string.soon)
        )

    override fun getResourceLayoutId() = R.layout.fragment_soon

    override fun onInitDataBinding() {

    }

}