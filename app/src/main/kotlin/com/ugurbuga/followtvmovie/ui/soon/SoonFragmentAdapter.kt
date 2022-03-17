package com.ugurbuga.followtvmovie.ui.soon

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.common.Util
import com.ugurbuga.followtvmovie.ui.soon.movie.SoonMovieListFragment

class SoonFragmentAdapter(
    var context: Context,
    fm: FragmentManager
) : FragmentStatePagerAdapter(fm) {

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                SoonMovieListFragment.newInstance()
            }
            1 -> {
                SoonMovieListFragment.newInstance()
            }
            else -> throw IllegalStateException("View Error")
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> context.getString(R.string.movies)

            1 -> context.getString(R.string.tv_shows)

            else -> Util.EMPTY_STRING
        }
    }
}