package com.ugurbuga.followtvmovie.ui.favorite

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.core.common.CommonUtil
import com.ugurbuga.followtvmovie.ui.movies.WatchLaterMovieListFragment
import com.ugurbuga.followtvmovie.ui.movies.WatchedMovieListFragment

class FavoriteMoviesFragmentAdapter(
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

            else -> CommonUtil.EMPTY_STRING
        }
    }
}