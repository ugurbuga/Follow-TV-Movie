package com.ugurbuga.followtvmovie.watch.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.ugurbuga.followtvmovie.watch.data.api.ApiConstants
import com.ugurbuga.followtvmovie.watch.data.api.services.MovieService
import com.ugurbuga.followtvmovie.watch.databinding.ActivityMovieDetailBinding
import com.ugurbuga.followtvmovie.watch.detail.model.MovieDetailResponse
import com.ugurbuga.followtvmovie.watch.util.Util
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    @Inject
    lateinit var movieService: MovieService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieId = intent.extras?.getString("movieId") ?: Util.EMPTY_STRING

        val call: Call<MovieDetailResponse> = movieService.getMovieDetail2(movieId)

        call.enqueue(object : Callback<MovieDetailResponse> {
            override fun onResponse(
                call: Call<MovieDetailResponse>, response: Response<MovieDetailResponse>
            ) {
                if (response.isSuccessful) {
                    Glide.with(binding.image)
                        .load(ApiConstants.BASE_IMAGE_URL + response.body()!!.posterPath)
                        .circleCrop()
                        .into(binding.image)
                    binding.title.text = response.body()!!.title
                    binding.detail.text = response.body()!!.overview
                }
            }

            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {

            }
        })

    }
}