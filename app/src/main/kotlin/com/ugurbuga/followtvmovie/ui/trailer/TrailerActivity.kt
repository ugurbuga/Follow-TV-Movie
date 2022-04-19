package com.ugurbuga.followtvmovie.ui.trailer

import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVMNavigationActivity
import com.ugurbuga.followtvmovie.databinding.ActivityBaseNavigationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrailerActivity :
    FTMBaseVMNavigationActivity<TrailerViewModel, ActivityBaseNavigationBinding>() {

    override fun getLayoutResourceId() = R.layout.activity_base_navigation

    override fun getNavigationGraph() = R.navigation.trailer_nav_graph

    override fun onInitDataBinding() {
        setNavigationStartDestination(startDestination = R.id.trailerFragment, intent.extras)
    }
}

