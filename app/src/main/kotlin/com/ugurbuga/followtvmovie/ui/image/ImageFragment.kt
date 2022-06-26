package com.ugurbuga.followtvmovie.ui.image

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVMFragment
import com.ugurbuga.followtvmovie.common.Argument
import com.ugurbuga.followtvmovie.databinding.FragmentImageBinding
import com.ugurbuga.followtvmovie.domain.image.model.ImageUIModel
import com.ugurbuga.followtvmovie.ui.moviedetail.ImageAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

@AndroidEntryPoint
class ImageFragment : FTMBaseVMFragment<ImageViewModel, FragmentImageBinding>() {

    override fun getResourceLayoutId() = R.layout.fragment_image

    override fun viewModelClass() = ImageViewModel::class.java

    private val args: ImageFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleOnBackPressed()
    }

    private fun handleOnBackPressed() {
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    setFragmentResult(
                        Argument.POSITION,
                        bundleOf(Argument.POSITION to viewBinding.viewPager.currentItem)
                    )

                    if (isEnabled) {
                        isEnabled = false
                        activity?.onBackPressed()
                    }
                }
            }
            )
    }

    override fun onInitDataBinding() {

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))

        compositePageTransformer.addTransformer { page, position ->
            val r = 1f.minus(abs(position))
            page.scaleY = 0.85f + r * 0.15f
        }

        val imageAdapter = ImageAdapter(isFullScreen = true)
        imageAdapter.submitList(args.images.toMutableList() as List<ImageUIModel>)
        viewBinding.viewPager.apply {
            adapter = imageAdapter
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 1
            setPageTransformer(compositePageTransformer)
            setCurrentItem(args.position, false)
        }

        viewBinding.toolbar.setNavigationClickListener {
            activity?.onBackPressed()
        }
    }
}