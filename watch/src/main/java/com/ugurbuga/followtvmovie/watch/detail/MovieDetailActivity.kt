package com.ugurbuga.followtvmovie.watch.detail

import android.app.Activity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.ugurbuga.followtvmovie.watch.MovieService
import com.ugurbuga.followtvmovie.watch.databinding.ActivityMovieDetailBinding
import com.ugurbuga.followtvmovie.watch.detail.model.MovieDetailResponse
import com.ugurbuga.followtvmovie.watch.util.Network
import com.ugurbuga.followtvmovie.watch.util.Util
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailActivity : Activity() {

    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var movieService = Network.provideRetrofit().create(MovieService::class.java)

        val movieId = intent.extras?.getString("movieId") ?: Util.EMPTY_STRING

        val call: Call<MovieDetailResponse> = movieService.getMovieDetail(movieId)

        call.enqueue(object : Callback<MovieDetailResponse> {
            override fun onResponse(
                call: Call<MovieDetailResponse>, response: Response<MovieDetailResponse>
            ) {
                if (response.isSuccessful) {
                    Glide.with(binding.image)
                        .load(Util.BASE_IMAGE_URL + response.body()!!.posterPath)
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