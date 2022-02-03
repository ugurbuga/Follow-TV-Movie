package com.ugurbuga.followtvmovie.ui.main

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVmDbActivity
import com.ugurbuga.followtvmovie.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FTMBaseVmDbActivity<MainViewModel, ActivityMainBinding>() {

    override fun getLayoutResourceId() = R.layout.activity_main

    override fun onInitDataBinding() {
        initGraph()
    }

    private fun initGraph() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment
        var navController = navHostFragment.navController

        viewBinding.mainBottomNav.setupWithNavController(navController)
    }

}