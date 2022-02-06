package com.ugurbuga.followtvmovie.ui.discover

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ugurbuga.followtvmovie.ui.discover.popularlist.PopularListFragment

class DiscoverFragmentAdapter(
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                PopularListFragment.newInstance(DiscoverType.MOVIE)
            }
            1 -> {
                PopularListFragment.newInstance(DiscoverType.TV_SHOW)
            }
            else -> throw IllegalStateException("View Error")
        }
    }

    override fun getItemCount(): Int = 2
}
