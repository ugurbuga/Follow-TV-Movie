package com.ugurbuga.followtvmovie.watch.ui.discover

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.wear.widget.WearableLinearLayoutManager
import com.ugurbuga.followtvmovie.watch.R
import com.ugurbuga.followtvmovie.watch.databinding.ActivityDiscoverBinding
import com.ugurbuga.followtvmovie.watch.ui.popularlist.PopularMoviesActivity
import com.ugurbuga.followtvmovie.watch.ui.popularlist.PopularMoviesViewModel
import com.ugurbuga.followtvmovie.watch.ui.upcoming.UpcomingMoviesActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiscoverActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityDiscoverBinding

    lateinit var discoverAdapter: DiscoverAdapter

    private val viewModel: PopularMoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_discover)
        viewBinding.lifecycleOwner = this

        discoverAdapter = DiscoverAdapter(::onItemClicked)

        with(viewBinding.discoverRecyclerView) {
            setHasFixedSize(true)
            isEdgeItemsCenteringEnabled = true
            layoutManager = WearableLinearLayoutManager(this@DiscoverActivity)
            adapter = discoverAdapter
        }

        val list = arrayListOf(
            DiscoverItem(
                ScreenType.POPULAR_MOVIES,
                getString(R.string.popular_movies),
                R.drawable.ic_movie
            ),
            DiscoverItem(
                ScreenType.COMING_SOON_MOVIES,
                getString(R.string.coming_soon_movies),
                R.drawable.ic_calendar_active
            ),
        )
        discoverAdapter.submitList(list)

    }

    private fun onItemClicked(item: DiscoverItem) {
        when (item.screenType) {
            ScreenType.POPULAR_MOVIES -> {
                startActivity(Intent(this, PopularMoviesActivity::class.java))
            }

            ScreenType.COMING_SOON_MOVIES -> {
                startActivity(Intent(this, UpcomingMoviesActivity::class.java))
            }
        }
    }
}