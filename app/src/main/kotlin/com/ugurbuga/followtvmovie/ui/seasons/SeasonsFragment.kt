package com.ugurbuga.followtvmovie.ui.seasons

import androidx.navigation.navGraphViewModels
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVMFragment
import com.ugurbuga.followtvmovie.databinding.FragmentSeasonsBinding
import com.ugurbuga.followtvmovie.domain.tvshowdetail.detail.SeasonUIModel
import com.ugurbuga.followtvmovie.extensions.collect
import com.ugurbuga.followtvmovie.ui.tvshowdetail.SharedTvShowViewModel
import com.ugurbuga.followtvmovie.ui.tvshowdetail.TvShowDetailViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeasonsFragment : FTMBaseVMFragment<SeasonsViewModel, FragmentSeasonsBinding>() {

    override fun getResourceLayoutId() = R.layout.fragment_seasons

    override fun viewModelClass() = SeasonsViewModel::class.java

    private val sharedTvShowViewModel: SharedTvShowViewModel by navGraphViewModels(R.id.tv_show_detail_nav_graph) {
        defaultViewModelProviderFactory
    }

    override fun onInitDataBinding() {
        viewBinding.seasonListRecyclerView.adapter = SeasonAdapter(::onSeasonClicked)
        collect(sharedTvShowViewModel.tvShowDetailViewState, ::onViewState)

        viewBinding.toolbar.setNavigationClickListener {
            popBack()
        }
    }

    private fun onSeasonClicked(season: SeasonUIModel) {
        navigate(SeasonsFragmentDirections.actionSeasonsToSeasonDetail(season.seasonNumber))
    }

    private fun onViewState(viewState: TvShowDetailViewState) {
        viewBinding.viewState = viewState
    }

}