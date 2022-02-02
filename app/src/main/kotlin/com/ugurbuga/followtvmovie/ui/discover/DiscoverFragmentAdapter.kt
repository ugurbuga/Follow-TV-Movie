package com.ugurbuga.followtvmovie.ui.discover

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ugurbuga.followtvmovie.ui.discover.popular.tv.PopularTvShowFragment

class DiscoverFragmentAdapter(
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                //TODO: change to movie
                PopularTvShowFragment.newInstance()
            }
            1 -> {
                PopularTvShowFragment.newInstance()
            }
            else -> throw IllegalStateException("View Error")
        }
    }

    override fun getItemCount(): Int = 2
}
