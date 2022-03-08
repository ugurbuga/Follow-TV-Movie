package com.ugurbuga.followtvmovie.ui.moviedetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.URLUtil
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.google.android.material.snackbar.Snackbar
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVMFragment
import com.ugurbuga.followtvmovie.bindings.setImageUrl
import com.ugurbuga.followtvmovie.common.AppPackageName
import com.ugurbuga.followtvmovie.common.Notifier
import com.ugurbuga.followtvmovie.data.api.ApiConstants
import com.ugurbuga.followtvmovie.databinding.FragmentMovieDetailBinding
import com.ugurbuga.followtvmovie.domain.moviedetail.image.ImageUIModel
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.CastUIModel
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.TrailerUIModel
import com.ugurbuga.followtvmovie.extensions.collect
import com.ugurbuga.followtvmovie.extensions.isPackageEnabled
import com.ugurbuga.followtvmovie.ui.moviedetail.cast.CastAdapter
import com.ugurbuga.followtvmovie.ui.moviedetail.genre.GenreAdapter
import com.ugurbuga.followtvmovie.ui.moviedetail.trailer.TrailerAdapter
import com.ugurbuga.followtvmovie.view.dialog.FTMDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : FTMBaseVMFragment<MovieDetailViewModel, FragmentMovieDetailBinding>() {

    override fun getResourceLayoutId() = R.layout.fragment_movie_detail

    override fun generateViewModel() = MovieDetailViewModel::class.java

    val args: MovieDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val animation = TransitionInflater.from(requireContext()).inflateTransition(
            android.R.transition.move
        )
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation
        deepLinkPush()
    }

    override fun onResume() {
        super.onResume()
        activity?.window?.statusBarColor =
            ContextCompat.getColor(requireContext(), android.R.color.transparent)
    }

    override fun onPause() {
        super.onPause()
        activity?.window?.statusBarColor =
            ContextCompat.getColor(requireContext(), R.color.quaternary_color)
    }

    override fun onInitDataBinding() {
        with(viewBinding) {
            imageRecyclerView.adapter = ImageAdapter(::onImageClicked)
            trailerRecyclerView.adapter = TrailerAdapter(::onTrailerClicked)
            castRecyclerView.adapter = CastAdapter(::onCastClicked)
            val adapter = GenreAdapter()
            genreRecyclerView.adapter = adapter
            imageView.setImageUrl(getImageUrl())

            favoriteButton.setOnClickListener {
                viewModel.changeFavoriteState()
            }

            reviewsButton.setOnClickListener {
                viewModel.reviewsClicked()
            }
            toolbar.setNavigationClickListener {
                popBack()
            }

            imdbButton.setOnClickListener {
                viewModel.imdbClicked(requireContext().isPackageEnabled(AppPackageName.IMDB))
            }

            facebookButton.setOnClickListener {
                viewModel.facebookClicked(requireContext().isPackageEnabled(AppPackageName.FACEBOOK))
            }

            twitterButton.setOnClickListener {
                viewModel.twitterClicked(requireContext().isPackageEnabled(AppPackageName.TWITTER))
            }

            instagramButton.setOnClickListener {
                viewModel.instagramClicked(requireContext().isPackageEnabled(AppPackageName.INSTAGRAM))
            }
        }

        collect(viewModel.movieDetailViewState, ::onMovieDetailViewState)
        collect(viewModel.movieDetailViewEvent, ::onMovieDetailViewEvent)
    }


    private fun deepLinkPush() {
        val context = requireContext().applicationContext
        val navController = findNavController()

        val pendingIntent = navController.createDeepLink().setDestination(R.id.movieDetailFragment)
            .setArguments(args.toBundle()).createPendingIntent()
        Notifier.postNotification(args.argId.toInt(), context, pendingIntent)
    }

    private fun getImageUrl(): String {
        return if (URLUtil.isValidUrl(args.argImageUrl)) {
            args.argImageUrl
        } else {
            ApiConstants.BASE_IMAGE_URL + "/" + args.argImageUrl
        }
    }

    private fun onImageClicked(imageUIModel: ImageUIModel, position: Int) {
        viewModel.imageClicked(position)
    }

    private fun onCastClicked(cast: CastUIModel, imageView: AppCompatImageView) {
        val extras = FragmentNavigatorExtras(imageView to getString(R.string.image_big))
        val directions = MovieDetailFragmentDirections.actionMovieDetailToPersonDetail(
            cast.id, cast.profilePath
        )
        navigate(directions, extras)
    }

    private fun onTrailerClicked(trailer: TrailerUIModel) {
        navigate(MovieDetailFragmentDirections.actionMovieDetailToTrailer(trailer.key))
    }

    private fun onMovieDetailViewEvent(event: MovieDetailViewEvent) {
        when (event) {
            is MovieDetailViewEvent.ShowSnackbar -> {
                Snackbar.make(viewBinding.root, getString(event.message), Snackbar.LENGTH_SHORT)
                    .show()
            }
            is MovieDetailViewEvent.NavigateToReviews -> {
                navigate(MovieDetailFragmentDirections.actionReviewFragment(event.movieId))
            }
            is MovieDetailViewEvent.NavigateToImages -> {
                navigate(
                    MovieDetailFragmentDirections.actionMovieDetailToImage(
                        event.imageList.toTypedArray(), event.position
                    )
                )
            }
            is MovieDetailViewEvent.NavigateToOtherApp -> {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(event.url)))
            }
            is MovieDetailViewEvent.NavigateToWebView -> {
                navigate(
                    MovieDetailFragmentDirections.actionMovieDetailToWeb(
                        event.url
                    )
                )
            }
            is MovieDetailViewEvent.ShowWatchedOrWatchLaterDialog -> {
                showWatchedOrWatchLaterDialog(event.movieName)
            }
        }
    }

    private fun showWatchedOrWatchLaterDialog(movieName: String) {
        val builder = FTMDialog(requireContext())
        builder.setTitle(movieName)

        builder.setNeutralButton(getString(R.string.watched)) { _, _ ->
            viewModel.addFavorite(isWatched = true)
        }
        builder.setPositiveButton(getString(R.string.watch_later)) { _, _ ->
            viewModel.addFavorite(isWatched = false)
        }

        val dialog = builder.create()
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }

    private fun onMovieDetailViewState(movieDetailViewState: MovieDetailViewState) {
        viewBinding.viewState = movieDetailViewState
    }
}
