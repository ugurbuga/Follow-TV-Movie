package com.ugurbuga.followtvmovie.ui.moviedetail

import android.os.Bundle
import android.transition.TransitionInflater
import com.google.android.material.snackbar.Snackbar
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVMFragment
import com.ugurbuga.followtvmovie.bindings.setImageUrl
import com.ugurbuga.followtvmovie.databinding.FragmentMovieDetailBinding
import com.ugurbuga.followtvmovie.domain.moviedetail.image.ImageUIModel
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.CastUIModel
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.TrailerUIModel
import com.ugurbuga.followtvmovie.extensions.collect
import com.ugurbuga.followtvmovie.ui.moviedetail.cast.CastAdapter
import com.ugurbuga.followtvmovie.ui.moviedetail.genre.GenreAdapter
import com.ugurbuga.followtvmovie.ui.moviedetail.trailer.TrailerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : FTMBaseVMFragment<MovieDetailViewModel, FragmentMovieDetailBinding>() {

    override fun getResourceLayoutId() = R.layout.fragment_movie_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val animation = TransitionInflater.from(requireContext()).inflateTransition(
            android.R.transition.move
        )
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation
    }

    override fun onInitDataBinding() {
        with(viewBinding) {
            imageRecyclerView.adapter = ImageAdapter(::onImageClicked)
            trailerRecyclerView.adapter = TrailerAdapter(::onTrailerClicked)
            castRecyclerView.adapter = CastAdapter(::onCastClicked)
            val adapter = GenreAdapter()
            genreRecyclerView.adapter = adapter
            imageView.setImageUrl(requireArguments().getString("arg_image_url", ""))

            favoriteButton.setOnClickListener {
                viewModel.changeFavoriteState()
            }

            reviewsButton.setOnClickListener {
                navigate(
                    MovieDetailFragmentDirections.actionReviewFragment(
                        requireArguments().getInt("arg_id", -1)
                    )
                )
            }
        }

        collect(viewModel.movieDetailViewState, ::onMovieDetailViewState)
        collect(viewModel.movieDetailViewEvent, ::onMovieDetailViewEvent)
    }

    private fun onImageClicked(imageUIModel: ImageUIModel, position: Int) {
        navigate(
            MovieDetailFragmentDirections.actionMovieDetailToImage(
                viewBinding.viewState!!.images.toTypedArray(), position
            )
        )
    }

    private fun onCastClicked(cast: CastUIModel) {
        navigate(MovieDetailFragmentDirections.actionMovieDetailToCastDetail(cast.creditId))
    }

    private fun onTrailerClicked(trailer: TrailerUIModel) {
        navigate(MovieDetailFragmentDirections.actionMovieDetailToTrailer(trailer.key))
    }

    private fun onMovieDetailViewEvent(event: MovieDetailViewEvent) {
        when (event) {
            MovieDetailViewEvent.ShowAddedSnackbar -> {
                Snackbar.make(
                    viewBinding.root, getString(R.string.added_favorite), Snackbar.LENGTH_SHORT
                ).show()
            }
            MovieDetailViewEvent.ShowDeletedSnackbar -> {
                Snackbar.make(
                    viewBinding.root, getString(R.string.removed_favorite), Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun onMovieDetailViewState(movieDetailViewState: MovieDetailViewState) {
        viewBinding.viewState = movieDetailViewState
    }
}
