package com.ugurbuga.followtvmovie.base

import android.os.Bundle
import androidx.annotation.NavigationRes
import androidx.databinding.ViewDataBinding
import androidx.navigation.fragment.NavHostFragment
import com.ugurbuga.followtvmovie.R

abstract class FTMBaseVMNavigationActivity<VM : FTMBaseViewModel, DB : ViewDataBinding> :
    FTMBaseVmDbActivity<VM, DB>() {
    @NavigationRes
    abstract fun getNavigationGraph(): Int

    fun findNavHostFragment(navHostId: Int) =
        supportFragmentManager.findFragmentById(navHostId) as NavHostFragment

    fun setNavigationStartDestination(
        startDestination: Int,
        bundle: Bundle? = null
    ) {
        val navHostFragment = findNavHostFragment(R.id.base_navigation_fragment_container_view)
        val graphInflater = navHostFragment.navController.navInflater
        val navGraph = graphInflater.inflate(getNavigationGraph())
        val navController = navHostFragment.navController

        navGraph.setStartDestination(startDestination)

        bundle?.let {
            navController.setGraph(navGraph, bundle)
        } ?: kotlin.run {
            navController.graph = navGraph
        }
    }
}
