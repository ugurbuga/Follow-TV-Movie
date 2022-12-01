package com.ugurbuga.followtvmovie.ui.persondetail

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.navArgs
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVMFragment
import com.ugurbuga.followtvmovie.bindings.setImageUrl
import com.ugurbuga.followtvmovie.common.Argument
import com.ugurbuga.followtvmovie.core.extensions.collect
import com.ugurbuga.followtvmovie.core.extensions.color
import com.ugurbuga.followtvmovie.databinding.FragmentPersonDetailBinding
import com.ugurbuga.followtvmovie.domain.image.model.ImageUIModel
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import com.ugurbuga.followtvmovie.ui.discover.MediaType
import com.ugurbuga.followtvmovie.ui.discover.adapter.PosterAdapter
import com.ugurbuga.followtvmovie.ui.moviedetail.ImageAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonDetailFragment :
    FTMBaseVMFragment<PersonDetailViewModel, FragmentPersonDetailBinding>() {

    override fun getResourceLayoutId() = R.layout.fragment_person_detail

    override fun viewModelClass() = PersonDetailViewModel::class.java

    val args: PersonDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val animation = TransitionInflater.from(requireContext()).inflateTransition(
            android.R.transition.move
        )
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation

        setFragmentResultListener(Argument.POSITION) { _, bundle ->
            val position = bundle.getInt(Argument.POSITION)
            viewBinding.imageRecyclerView.scrollToPosition(position)
        }
    }

    override fun onResume() {
        super.onResume()
        activity?.window?.statusBarColor = color(android.R.color.transparent)
    }

    override fun onPause() {
        super.onPause()
        activity?.window?.statusBarColor = color(R.color.quaternary_color)
    }

    override fun onInitDataBinding() {
        collect(viewModel.personDetailViewState, ::onPersonDetailViewState)
        collect(viewModel.personDetailViewEvent, ::onPersonDetailViewEvent)
        with(viewBinding) {
            imageView.setImageUrl(args.imageUrl)
            imageRecyclerView.adapter = ImageAdapter(::onImageClicked)
            knownForRecyclerView.adapter = PosterAdapter(::onPosterClicked)

            toolbar.setNavigationClickListener {
                popBack()
            }
        }
    }

    private fun onPersonDetailViewEvent(event: PersonDetailViewEvent) {
        when (event) {
            is PersonDetailViewEvent.NavigateToImages -> {
                navigate(
                    PersonDetailFragmentDirections.actionPersonDetailToImage(
                        event.imageList.toTypedArray(), event.position
                    )
                )
            }
        }
    }

    private fun onImageClicked(imageUIModel: ImageUIModel, position: Int) {
        viewModel.imageClicked(position)
    }

    private fun onPosterClicked(
        poster: PosterItemUIModel,
        imageView: AppCompatImageView
    ) {
        val extras = FragmentNavigatorExtras(imageView to getString(R.string.image_big))

        when (poster.mediaType) {
            MediaType.TV -> {
                val directions = PersonDetailFragmentDirections.actionPersonDetailToTvShowDetail(
                    poster.id, poster.posterPath
                )
                navigate(directions, extras)
            }

            MediaType.MOVIE -> {
                val directions = PersonDetailFragmentDirections.actionPersonDetailToMovieDetail(
                    poster.id, poster.posterPath
                )
                navigate(directions, extras)
            }
        }
    }

    private fun onPersonDetailViewState(personDetailViewState: PersonDetailViewState) {
        viewBinding.viewState = personDetailViewState
    }
}
