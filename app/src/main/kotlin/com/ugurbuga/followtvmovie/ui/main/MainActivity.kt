package com.ugurbuga.followtvmovie.ui.main

import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVmDbActivity
import com.ugurbuga.followtvmovie.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FTMBaseVmDbActivity<MainViewModel, ActivityMainBinding>() {

    private lateinit var navController: NavController

    override fun getLayoutResourceId() = R.layout.activity_main

    override fun onInitDataBinding() {
        initGraph()
    }

    private fun initGraph() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment
        navController = navHostFragment.navController

        viewBinding.mainBottomNav.setupWithNavController(navController)

        viewBinding.mainBottomNav.setOnItemReselectedListener {
            when (it.itemId) {
                R.id.discover_nav_graph -> {
                    popBack(R.id.discoverFragment)
                }

                R.id.tv_series_nav_graph -> {
                    popBack(R.id.tvSeriesFragment)
                }

                R.id.movie_nav_graph -> {
                    popBack(R.id.movieFragment)
                }

                R.id.soon_nav_graph -> {
                    popBack(R.id.soonFragment)
                }
            }
        }
    }

    private fun popBack(@IdRes destinationId: Int, inclusive: Boolean = false) {
        navController.popBackStack(destinationId, inclusive)
    }

}