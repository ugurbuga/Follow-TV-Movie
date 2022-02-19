package com.ugurbuga.followtvmovie.ui.moviedetail

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.snackbar.Snackbar
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVMFragment
import com.ugurbuga.followtvmovie.bindings.setImageUrl
import com.ugurbuga.followtvmovie.databinding.FragmentMovieDetailBinding
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.TrailerUIModel
import com.ugurbuga.followtvmovie.extensions.observe
import com.ugurbuga.followtvmovie.extensions.observeEvent
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
            trailerRecyclerView.adapter = TrailerAdapter(::onTrailerClicked)
            val adapter = GenreAdapter()
            genreRecyclerView.adapter = adapter
            imageView.setImageUrl(requireArguments().getString("arg_image_url", ""))
            collapsingToolbarLayout.apply {
                setCollapsedTitleTypeface(
                    ResourcesCompat.getFont(
                        requireContext(), R.font.sofiapro_semibold
                    )
                )
                setExpandedTitleTypeface(
                    ResourcesCompat.getFont(
                        requireContext(), R.font.sofiapro_semibold
                    )
                )
            }

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

        observe(viewModel.movieDetailViewState, ::onMovieDetailViewState)
        observeEvent(viewModel.movieDetailViewEvent, ::onMovieDetailViewEvent)
    }

    private fun onTrailerClicked(trailer: TrailerUIModel) {
        navigate(MovieDetailFragmentDirections.actionMovieDetailToTrailer(trailer.key))
        //startActivity(TrailerActivity.newIntent(requireContext(), trailer.key))
    }

    private fun onMovieDetailViewEvent(event: MovieDetailViewEvent) {
        when (event) {
            MovieDetailViewEvent.ShowAddedSnackbar -> {
                Snackbar.make(viewBinding.root, "Favoriye Eklendi.", Snackbar.LENGTH_SHORT).show()
            }
            MovieDetailViewEvent.ShowDeletedSnackbar -> {
                Snackbar.make(viewBinding.root, "Favoriden Kaldırıldı.", Snackbar.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun onMovieDetailViewState(movieDetailViewState: MovieDetailViewState) {
        viewBinding.viewState = movieDetailViewState
    }
}
