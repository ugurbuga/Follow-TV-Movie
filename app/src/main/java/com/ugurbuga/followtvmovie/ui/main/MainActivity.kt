package com.ugurbuga.followtvmovie.ui.main

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.BaseVmDbActivity
import com.ugurbuga.followtvmovie.databinding.ActivityMainBinding
import com.ugurbuga.followtvmovie.view.toolbar.FTMToolbarType

class MainActivity : BaseVmDbActivity<MainViewModel, ActivityMainBinding>() {
    override fun getToolbarType() = FTMToolbarType.BaseToolbar

    override fun getViewModel() = MainViewModel::class.java

    override fun getViewBinding() = ActivityMainBinding::class.java

    override fun onInitDataBinding() {
        initGraph()
    }

    private fun initGraph() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment
        var navController = navHostFragment.navController

        mViewBinding.mainBottomNav.setupWithNavController(navController)
    }

}