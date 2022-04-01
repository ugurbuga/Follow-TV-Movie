package com.ugurbuga.followtvmovie.watch.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ugurbuga.followtvmovie.watch.R
import com.ugurbuga.followtvmovie.watch.data.api.services.MovieService
import com.ugurbuga.followtvmovie.watch.databinding.ActivityMovieDetailBinding
import com.ugurbuga.followtvmovie.watch.extensions.collect
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMovieDetailBinding

    val viewModel: MovieDetailViewModel by viewModels()

    @Inject
    lateinit var movieService: MovieService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)
        viewBinding.lifecycleOwner = this

        collect(viewModel.movieDetailViewState) {
            viewBinding.viewState = it
        }
    }
}