package com.ugurbuga.followtvmovie.ui.cast

import android.os.Bundle
import android.transition.TransitionInflater
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVMFragment
import com.ugurbuga.followtvmovie.databinding.FragmentCastDetailBinding
import com.ugurbuga.followtvmovie.ui.moviedetail.cast.CastDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CastDetailFragment : FTMBaseVMFragment<CastDetailViewModel, FragmentCastDetailBinding>() {

    override fun getResourceLayoutId() = R.layout.fragment_cast_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val animation = TransitionInflater.from(requireContext()).inflateTransition(
            android.R.transition.move
        )
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation
    }

    override fun onInitDataBinding() {
        with(viewBinding) {

        }

    }
}
