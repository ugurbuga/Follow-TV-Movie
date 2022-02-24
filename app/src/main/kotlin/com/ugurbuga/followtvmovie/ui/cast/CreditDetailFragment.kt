package com.ugurbuga.followtvmovie.ui.cast

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.appcompat.widget.AppCompatImageView
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.navArgs
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVMFragment
import com.ugurbuga.followtvmovie.bindings.setImageUrl
import com.ugurbuga.followtvmovie.databinding.FragmentCreditDetailBinding
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import com.ugurbuga.followtvmovie.extensions.collect
import com.ugurbuga.followtvmovie.ui.discover.adapter.PosterAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreditDetailFragment :
    FTMBaseVMFragment<CreditDetailViewModel, FragmentCreditDetailBinding>() {

    override fun getResourceLayoutId() = R.layout.fragment_credit_detail

    val args: CreditDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val animation = TransitionInflater.from(requireContext()).inflateTransition(
            android.R.transition.move
        )
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation
    }

    override fun onInitDataBinding() {
        collect(viewModel.creditDetailViewState, ::onCreditDetailViewState)
        with(viewBinding) {
            imageView.setImageUrl(args.argImageUrl)

            knownForRecyclerView.adapter = PosterAdapter(::onPosterClicked)

            toolbar.setNavigationClickListener {
                popBack()
            }
        }
    }

    private fun onPosterClicked(
        poster: PosterItemUIModel,
        imageView: AppCompatImageView
    ) {
        val extras = FragmentNavigatorExtras(imageView to getString(R.string.image_big))
        val directions =
            CreditDetailFragmentDirections.actionCreditDetailToMovieDetail(
                poster.id,
                poster.posterPath
            )
        navigate(directions, extras)
    }

    private fun onCreditDetailViewState(creditDetailViewState: CreditDetailViewState) {
        viewBinding.viewState = creditDetailViewState
    }
}
