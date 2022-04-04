package com.ugurbuga.followtvmovie.watch.ui.upcoming

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.wear.widget.WearableLinearLayoutManager
import com.ugurbuga.followtvmovie.watch.R
import com.ugurbuga.followtvmovie.watch.databinding.ActivityUpcomingMoviesBinding
import com.ugurbuga.followtvmovie.watch.domain.popularlist.MovieResponse
import com.ugurbuga.followtvmovie.watch.extensions.collect
import com.ugurbuga.followtvmovie.watch.extensions.scrollEndListener
import com.ugurbuga.followtvmovie.watch.ui.detail.MovieDetailActivity
import com.ugurbuga.followtvmovie.watch.ui.popularlist.adapter.PosterAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpcomingMoviesActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityUpcomingMoviesBinding

    lateinit var posterAdapter: PosterAdapter

    private val viewModel: UpcomingMoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_upcoming_movies)
        viewBinding.lifecycleOwner = this

        posterAdapter = PosterAdapter(::onPosterClicked)

        with(viewBinding.movieRecyclerView) {
            setHasFixedSize(true)
            isEdgeItemsCenteringEnabled = true
            layoutManager = WearableLinearLayoutManager(this@UpcomingMoviesActivity)
            scrollEndListener {
                viewModel.addLoadingAndGetNewItems()
            }
            adapter = posterAdapter
        }

        collect(viewModel.comingSoonMovies) {
            posterAdapter.submitList(it)
        }

    }

    private fun onPosterClicked(movieResponse: MovieResponse) {
        startActivity(Intent(this, MovieDetailActivity::class.java).apply {
            putExtra("movieId", movieResponse.id)
        })
    }
}