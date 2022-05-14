package com.ugurbuga.followtvmovie.ui.main

import androidx.annotation.IdRes
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVmDbActivity
import com.ugurbuga.followtvmovie.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class MainActivity : FTMBaseVmDbActivity<MainViewModel, ActivityMainBinding>() {

    private lateinit var navController: NavController

    override fun getLayoutResourceId() = R.layout.activity_main

    override fun setViewDataBinding() {
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isLoading.value
            }
        }
        super.setViewDataBinding()
    }

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

                R.id.tv_shows_nav_graph -> {
                    popBack(R.id.tvShowsFragment)
                }

                R.id.movies_nav_graph -> {
                    popBack(R.id.moviesFragment)
                }

                R.id.soon_nav_graph -> {
                    popBack(R.id.soonFragment)
                }

                R.id.persons_nav_graph -> {
                    popBack(R.id.personsFragment)
                }
            }
        }
    }

    private fun popBack(@IdRes destinationId: Int, inclusive: Boolean = false) {
        navController.popBackStack(destinationId, inclusive)
    }
}