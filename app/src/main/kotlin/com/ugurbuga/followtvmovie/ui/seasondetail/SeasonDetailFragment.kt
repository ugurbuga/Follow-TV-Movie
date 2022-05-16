package com.ugurbuga.followtvmovie.ui.seasondetail

import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVMFragment
import com.ugurbuga.followtvmovie.bindings.setImageUrl
import com.ugurbuga.followtvmovie.databinding.FragmentSeasonDetailBinding
import com.ugurbuga.followtvmovie.domain.seasondetail.model.EpisodeUIModel
import com.ugurbuga.followtvmovie.extensions.collect
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeasonDetailFragment :
    FTMBaseVMFragment<SeasonDetailViewModel, FragmentSeasonDetailBinding>() {

    override fun getResourceLayoutId() = R.layout.fragment_season_detail

    override fun viewModelClass() = SeasonDetailViewModel::class.java

    private val args: SeasonDetailFragmentArgs by navArgs()

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
        collect(viewModel.seasonDetailViewState, ::onViewState)

        with(viewBinding) {

            imageView.setImageUrl(args.imageUrl)

            episodesRecyclerview.adapter =
                EpisodeAdapter(requireContext(), ::onEpisodeClicked)

            toolbar.setNavigationClickListener {
                popBack()
            }
        }
    }

    private fun onViewState(viewState: SeasonDetailViewState) {
        viewBinding.viewState = viewState
    }

    private fun onEpisodeClicked(episode: EpisodeUIModel, appCompatImageView: AppCompatImageView) {

    }

}