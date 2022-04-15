package com.ugurbuga.followtvmovie.ui.tvshowdetail

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
import com.ugurbuga.followtvmovie.databinding.FragmentTvShowDetailBinding
import com.ugurbuga.followtvmovie.domain.moviedetail.image.ImageUIModel
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.CastUIModel
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.TrailerUIModel
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import com.ugurbuga.followtvmovie.extensions.collect
import com.ugurbuga.followtvmovie.extensions.isPackageEnabled
import com.ugurbuga.followtvmovie.extensions.scrollEndListener
import com.ugurbuga.followtvmovie.ui.discover.adapter.PosterAdapter
import com.ugurbuga.followtvmovie.ui.moviedetail.ImageAdapter
import com.ugurbuga.followtvmovie.ui.moviedetail.cast.CastAdapter
import com.ugurbuga.followtvmovie.ui.moviedetail.genre.GenreAdapter
import com.ugurbuga.followtvmovie.ui.moviedetail.trailer.TrailerAdapter
import com.ugurbuga.followtvmovie.view.dialog.FTMDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowDetailFragment : FTMBaseVMFragment<TvShowDetailViewModel, FragmentTvShowDetailBinding>() {

    override fun getResourceLayoutId() = R.layout.fragment_tv_show_detail

    override fun viewModelClass() = TvShowDetailViewModel::class.java

    val args: TvShowDetailFragmentArgs by navArgs()

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
            imageRecyclerView.adapter = ImageAdapter(::onImageClicked)
            trailerRecyclerView.adapter = TrailerAdapter(::onTrailerClicked)
            castRecyclerView.adapter = CastAdapter(::onCastClicked)
            genreRecyclerView.adapter = GenreAdapter()

            recommendationRecyclerView.apply {
                adapter = PosterAdapter(::onTvShowClicked)
                scrollEndListener {
                    viewModel.getNewRecommendations()
                }
            }

            similarTvShowsRecyclerView.apply {
                adapter = PosterAdapter(::onTvShowClicked)
                scrollEndListener {
                    viewModel.getNewSimilarTvShows()
                }
            }

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

        collect(viewModel.tvShowDetailViewState, ::onTvShowDetailViewState)
        collect(viewModel.tvShowDetailViewEvent, ::onTvShowDetailViewEvent)
    }

    private fun onTvShowClicked(poster: PosterItemUIModel, imageView: AppCompatImageView) {
        val extras = FragmentNavigatorExtras(imageView to getString(R.string.image_big))
        val directions =
            TvShowDetailFragmentDirections.actionTvShowDetailToTvShowDetail(
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
        val directions = TvShowDetailFragmentDirections.actionTvShowDetailToPersonDetail(
            cast.id, cast.profilePath
        )
        navigate(directions, extras)
    }

    private fun onTrailerClicked(trailer: TrailerUIModel) {
        navigate(TvShowDetailFragmentDirections.actionTvShowDetailToTrailer(trailer.key))
    }

    private fun onTvShowDetailViewEvent(event: TvShowDetailViewEvent) {
        when (event) {
            is TvShowDetailViewEvent.ShowSnackbar -> {
                Snackbar.make(viewBinding.root, getString(event.message), Snackbar.LENGTH_SHORT)
                    .show()
            }
            is TvShowDetailViewEvent.NavigateToReviews -> {
                navigate(TvShowDetailFragmentDirections.actionReviewFragment(event.tvShowI))
            }
            is TvShowDetailViewEvent.NavigateToImages -> {
                navigate(
                    TvShowDetailFragmentDirections.actionTvShowDetailToImage(
                        event.imageList.toTypedArray(), event.position
                    )
                )
            }
            is TvShowDetailViewEvent.NavigateToOtherApp -> {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(event.url)))
            }
            is TvShowDetailViewEvent.NavigateToWebView -> {
                navigate(
                    TvShowDetailFragmentDirections.actionTvShowDetailToWeb(
                        event.url
                    )
                )
            }
            is TvShowDetailViewEvent.ShowWatchedOrWatchLaterDialog -> {
                showWatchedOrWatchLaterDialog(event.tvShowName)
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

    private fun onTvShowDetailViewState(movieDetailViewState: TvShowDetailViewState) {
        viewBinding.viewState = movieDetailViewState
        movieDetailViewState.tvShowDetail?.let {
            //deepLinkPush(it.id, it.title, it.releaseDate)
        }
    }

    private fun deepLinkPush(id: String, title: String, releaseDate: String) {
        val context = requireContext().applicationContext
        val navController = findNavController()

        val pendingIntent = navController.createDeepLink()
            .setDestination(R.id.movieDetailFragment)
            .setArguments(args.toBundle())
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
