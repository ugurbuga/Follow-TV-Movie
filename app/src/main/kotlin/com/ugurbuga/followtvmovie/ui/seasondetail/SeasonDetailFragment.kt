package com.ugurbuga.followtvmovie.ui.seasondetail

import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVMFragment
import com.ugurbuga.followtvmovie.databinding.FragmentSeasonDetailBinding
import com.ugurbuga.followtvmovie.domain.tvshowdetail.detail.SeasonUIModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeasonDetailFragment : FTMBaseVMFragment<SeasonsDetailViewModel, FragmentSeasonDetailBinding>() {

    override fun getResourceLayoutId() = R.layout.fragment_season_detail

    override fun viewModelClass() = SeasonsDetailViewModel::class.java

    override fun onInitDataBinding() {
        viewBinding.episodesRecyclerview.adapter = EpisodeAdapter(::onEpisodeClicked)

        viewBinding.toolbar.setNavigationClickListener {
            popBack()
        }
    }

    private fun onEpisodeClicked(season: SeasonUIModel) {

    }

}