package com.ugurbuga.followtvmovie.ui.movie

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.common.Util

class FavoriteFragmentAdapter(
    var context: Context,
    fm: FragmentManager
) : FragmentStatePagerAdapter(fm) {

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                WatchLaterMovieListFragment.newInstance()
            }
            1 -> {
                WatchedMovieListFragment.newInstance()
            }
            else -> throw IllegalStateException("View Error")
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> context.getString(R.string.watch_later)

            1 -> context.getString(R.string.watched)

            else -> Util.EMPTY_STRING
        }
    }
}