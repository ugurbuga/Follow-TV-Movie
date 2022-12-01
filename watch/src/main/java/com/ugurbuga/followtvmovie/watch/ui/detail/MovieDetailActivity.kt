package com.ugurbuga.followtvmovie.watch.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ugurbuga.followtvmovie.core.extensions.collect
import com.ugurbuga.followtvmovie.watch.R
import com.ugurbuga.followtvmovie.watch.databinding.ActivityMovieDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMovieDetailBinding

    private val viewModel: MovieDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)
        viewBinding.lifecycleOwner = this

        collect(viewModel.movieDetailViewState) {
            viewBinding.viewState = it
        }

        viewBinding.tileButton.setOnClickListener {
            viewModel.tileClicked()
        }
    }
}