package com.ugurbuga.followtvmovie.ui.moviedetail

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVMFragment
import com.ugurbuga.followtvmovie.bindings.setImageUrl
import com.ugurbuga.followtvmovie.databinding.FragmentMovieDetailBinding
import com.ugurbuga.followtvmovie.extensions.observe
import com.ugurbuga.followtvmovie.extensions.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : FTMBaseVMFragment<MovieDetailViewModel, FragmentMovieDetailBinding>() {

    override fun getResourceLayoutId() = R.layout.fragment_movie_detail

    private val args: MovieDetailFragmentArgs by navArgs()

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
            val adapter = GenreAdapter()
            genreAdapterRecyclerView.adapter = adapter
            imageView.setImageUrl(args.argImageUrl)
            collapsingToolbarLayout.apply {
                setCollapsedTitleTypeface(
                    ResourcesCompat.getFont(
                        requireContext(),
                        R.font.sofiapro_semibold
                    )
                )
                setExpandedTitleTypeface(
                    ResourcesCompat.getFont(
                        requireContext(),
                        R.font.sofiapro_semibold
                    )
                )
            }

            favoriteButton.setOnClickListener {
                viewModel.changeFavoriteState()
            }
        }
        observe(viewModel.movieDetailViewState, ::onMovieDetailViewState)
        observeEvent(viewModel.movieDetailViewEvent, ::onMovieDetailViewEvent)

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
