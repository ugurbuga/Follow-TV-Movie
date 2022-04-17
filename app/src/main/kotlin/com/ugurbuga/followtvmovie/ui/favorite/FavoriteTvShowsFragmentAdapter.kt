package com.ugurbuga.followtvmovie.ui.favorite

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.common.Util
import com.ugurbuga.followtvmovie.ui.tvshows.ContinuedTvShowListFragment
import com.ugurbuga.followtvmovie.ui.tvshows.FinishedTvShowListFragment

class FavoriteTvShowsFragmentAdapter(
    var context: Context,
    fm: FragmentManager
) : FragmentStatePagerAdapter(fm) {

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                ContinuedTvShowListFragment.newInstance()
            }
            1 -> {
                FinishedTvShowListFragment.newInstance()
            }
            else -> throw IllegalStateException("View Error")
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> context.getString(R.string.continued)

            1 -> context.getString(R.string.finished)

            else -> Util.EMPTY_STRING
        }
    }
}