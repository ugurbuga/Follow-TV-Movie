package com.ugurbuga.followtvmovie.ui.video

import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVMNavigationActivity
import com.ugurbuga.followtvmovie.databinding.ActivityBaseNavigationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideoActivity :
    FTMBaseVMNavigationActivity<VideoViewModel, ActivityBaseNavigationBinding>() {

    override fun getLayoutResourceId() = R.layout.activity_base_navigation

    override fun getNavigationGraph() = R.navigation.video_nav_graph

    override fun onInitDataBinding() {
        setNavigationStartDestination(startDestination = R.id.videoFragment, intent.extras)
    }
}

