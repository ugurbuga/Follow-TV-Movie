package com.ugurbuga.followtvmovie.ui.image

import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVMFragment
import com.ugurbuga.followtvmovie.databinding.FragmentImageBinding
import com.ugurbuga.followtvmovie.domain.moviedetail.image.ImageUIModel
import com.ugurbuga.followtvmovie.ui.moviedetail.ImageAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

@AndroidEntryPoint
class ImageFragment : FTMBaseVMFragment<ImageViewModel, FragmentImageBinding>() {

    override fun getResourceLayoutId() = R.layout.fragment_image

    override fun generateViewModel() = ImageViewModel::class.java

    private val args: ImageFragmentArgs by navArgs()

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
            offscreenPageLimit = 3
            currentItem = args.position
            setPageTransformer(compositePageTransformer)
        }

        viewBinding.toolbar.setNavigationClickListener {
            popBack()
        }
    }
}