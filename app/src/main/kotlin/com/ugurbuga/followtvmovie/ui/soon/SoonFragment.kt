package com.ugurbuga.followtvmovie.ui.soon

import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVMFragment
import com.ugurbuga.followtvmovie.databinding.FragmentSoonBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SoonFragment : FTMBaseVMFragment<SoonViewModel, FragmentSoonBinding>() {

    override fun getResourceLayoutId() = R.layout.fragment_soon

    override fun viewModelClass() = SoonViewModel::class.java

    private val adapter: SoonFragmentAdapter by lazy {
        SoonFragmentAdapter(
            requireContext(),
            childFragmentManager
        )
    }

    override fun onInitDataBinding() {
        with(viewBinding) {
            viewPager.adapter = adapter
        }
    }

}