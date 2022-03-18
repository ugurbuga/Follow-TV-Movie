package com.ugurbuga.followtvmovie.watch.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.ugurbuga.followtvmovie.watch.data.api.ApiConstants
import com.ugurbuga.followtvmovie.watch.data.api.services.MovieService
import com.ugurbuga.followtvmovie.watch.databinding.ActivityMovieDetailBinding
import com.ugurbuga.followtvmovie.watch.popularlist.collect
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    val viewModel: MovieDetailViewModel by viewModels()

    @Inject
    lateinit var movieService: MovieService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        collect(viewModel.movieDetail) {
            Glide.with(binding.image)
                .load(ApiConstants.BASE_IMAGE_URL + it?.posterPath)
                .circleCrop().into(binding.image)
            binding.title.text = it?.title
            binding.detail.text = it?.overview
        }
    }
}