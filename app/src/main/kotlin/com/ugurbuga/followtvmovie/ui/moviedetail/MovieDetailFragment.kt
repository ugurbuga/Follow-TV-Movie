package com.ugurbuga.followtvmovie.ui.moviedetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.URLUtil
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.google.android.material.snackbar.Snackbar
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVMFragment
import com.ugurbuga.followtvmovie.bindings.setImageUrl
import com.ugurbuga.followtvmovie.common.AppPackageName
import com.ugurbuga.followtvmovie.common.Argument
import com.ugurbuga.followtvmovie.common.Notifier
import com.ugurbuga.followtvmovie.data.api.ApiConstants
import com.ugurbuga.followtvmovie.databinding.FragmentMovieDetailBinding
import com.ugurbuga.followtvmovie.domain.image.model.ImageUIModel
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.CastUIModel
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.VideoUIModel
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import com.ugurbuga.followtvmovie.extensions.collect
import com.ugurbuga.followtvmovie.extensions.isPackageEnabled
import com.ugurbuga.followtvmovie.extensions.scrollEndListener
import com.ugurbuga.followtvmovie.ui.discover.MediaType
import com.ugurbuga.followtvmovie.ui.discover.adapter.PosterAdapter
import com.ugurbuga.followtvmovie.ui.moviedetail.cast.CastAdapter
import com.ugurbuga.followtvmovie.ui.moviedetail.genre.GenreAdapter
import com.ugurbuga.followtvmovie.ui.moviedetail.video.VideoAdapter
import com.ugurbuga.followtvmovie.ui.video.VideoActivity
import com.ugurbuga.followtvmovie.view.dialog.FTMDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : FTMBaseVMFragment<MovieDetailViewModel, FragmentMovieDetailBinding>() {

    override fun getResourceLayoutId() = R.layout.fragment_movie_detail

    override fun viewModelClass() = MovieDetailViewModel::class.java

    private val args: MovieDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val animation = TransitionInflater.from(requireContext()).inflateTransition(
            android.R.transition.move
        )
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation
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
            genreRecyclerView.adapter = GenreAdapter()

            imageView.setImageUrl(getImageUrl())

            favoriteButton.setOnClickListener {
                viewModel.changeFavoriteState()
            }

            toolbar.setNavigationClickListener {
                popBack()
            }

            with(movieDetail) {
                imageRecyclerView.adapter = ImageAdapter(::onImageClicked)
                videosRecyclerView.adapter = VideoAdapter(::onVideoClicked)
                castRecyclerView.adapter = CastAdapter(::onCastClicked)

                recommendationRecyclerView.apply {
                    adapter = PosterAdapter(::onMovieClicked)
                    scrollEndListener {
                        viewModel.getNewRecommendations()
                    }
                }

                similarMoviesRecyclerView.apply {
                    adapter = PosterAdapter(::onMovieClicked)
                    scrollEndListener {
                        viewModel.getNewSimilar()
                    }
                }

                reviewsButton.setOnClickListener {
                    viewModel.reviewsClicked()
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
        }

        collect(viewModel.movieDetailViewState, ::onMovieDetailViewState)
        collect(viewModel.commonViewState, ::onCommonViewState)
        collect(viewModel.commonViewEvent, ::onCommonViewEvent)
    }

    private fun onMovieClicked(poster: PosterItemUIModel, imageView: AppCompatImageView) {
        val extras = FragmentNavigatorExtras(imageView to getString(R.string.image_big))
        val directions =
            MovieDetailFragmentDirections.actionMovieDetailToMovieDetail(
                poster.id,
                poster.posterPath
            )
        navigate(directions, extras)
    }

    private fun getImageUrl(): String {
        return if (URLUtil.isValidUrl(args.imageUrl)) {
            args.imageUrl
        } else {
            ApiConstants.BASE_IMAGE_URL + "/" + args.imageUrl
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

    private fun onVideoClicked(video: VideoUIModel) {
        navigate(MovieDetailFragmentDirections.actionMovieDetailToVideo(video.key))
    }

    private fun onCommonViewEvent(event: CommonViewEvent) {
        when (event) {
            is CommonViewEvent.ShowSnackbar -> {
                Snackbar.make(viewBinding.root, getString(event.message), Snackbar.LENGTH_SHORT)
                    .show()
            }
            is CommonViewEvent.NavigateToReviews -> {
                navigate(
                    MovieDetailFragmentDirections.actionReviewFragment(
                        event.id,
                        MediaType.MOVIE
                    )
                )
            }
            is CommonViewEvent.NavigateToImages -> {
                navigate(
                    MovieDetailFragmentDirections.actionMovieDetailToImage(
                        event.imageList.toTypedArray(), event.position
                    )
                )
            }
            is CommonViewEvent.NavigateToOtherApp -> {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(event.url)))
            }
            is CommonViewEvent.NavigateToWebView -> {
                navigate(
                    MovieDetailFragmentDirections.actionMovieDetailToWeb(
                        event.url
                    )
                )
            }
            is CommonViewEvent.ShowWatchedOrWatchLaterDialog -> {
                showWatchedOrWatchLaterDialog(event.name)
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

    private fun onCommonViewState(commonViewState: CommonViewState) {
        viewBinding.commonViewState = commonViewState
    }

    private fun deepLinkPush(id: String, title: String, releaseDate: String, urlKey: String) {
        val context = requireContext().applicationContext
        val navController = findNavController()

        /*val pendingIntent = navController.createDeepLink()
            .setDestination(R.id.movieDetailFragment)
            .setArguments(args.toBundle())
            .createPendingIntent()*/

        val pendingIntent = NavDeepLinkBuilder(requireContext())
            .setGraph(R.navigation.video_nav_graph)
            .setDestination(R.id.videoFragment)
            .setArguments(bundleOf(Argument.URL_KEY to urlKey))
            .setComponentName(VideoActivity::class.java)
            .createPendingIntent()

        Notifier.postNotification(
            id = id.toInt(),
            title = title,
            imageUrl = args.imageUrl,
            content = releaseDate,
            context = context,
            intent = pendingIntent
        )
    }
}
