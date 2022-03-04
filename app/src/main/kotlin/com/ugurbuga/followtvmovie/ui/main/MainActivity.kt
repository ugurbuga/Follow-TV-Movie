package com.ugurbuga.followtvmovie.ui.main

import android.os.Build
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVmDbActivity
import com.ugurbuga.followtvmovie.data.preferences.FTMPreferenceManager
import com.ugurbuga.followtvmovie.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class MainActivity : FTMBaseVmDbActivity<MainViewModel, ActivityMainBinding>() {

    private lateinit var navController: NavController

    override fun getLayoutResourceId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        setAppTheme(FTMPreferenceManager(this).getTheme())
        super.onCreate(savedInstanceState)
        installSplashScreen()
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

    open fun setAppTheme(theme: Int) {
        when (theme) {
            AppCompatDelegate.MODE_NIGHT_YES -> AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_YES
            )

            AppCompatDelegate.MODE_NIGHT_NO -> AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO
            )

            else -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
                }
            }
        }
    }

}