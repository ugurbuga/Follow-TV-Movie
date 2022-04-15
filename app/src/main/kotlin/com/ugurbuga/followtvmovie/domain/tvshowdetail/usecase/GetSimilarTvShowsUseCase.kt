package com.ugurbuga.followtvmovie.domain.tvshowdetail.usecase

import com.ugurbuga.followtvmovie.base.FTMUseCase
import com.ugurbuga.followtvmovie.common.Resource
import com.ugurbuga.followtvmovie.common.map
import com.ugurbuga.followtvmovie.domain.poster.mapper.PosterMapper
import com.ugurbuga.followtvmovie.domain.poster.model.PosterUIModel
import com.ugurbuga.followtvmovie.repository.tvshow.TvShowRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSimilarTvShowsUseCase @Inject constructor(
    private val tvShowRepository: TvShowRepository,
    private val posterMapper: PosterMapper
) :
    FTMUseCase<GetSimilarTvShowsUseCase.SimilarTvShow, PosterUIModel>() {

    data class SimilarTvShow(var tvShowId: String, val page: Int)

    override fun execute(params: SimilarTvShow): Flow<Resource<PosterUIModel>> {
        return tvShowRepository.getSimilarTvShows(params.tvShowId, params.page).map {
            it.map { response -> posterMapper.toPosterUIModel(response) }
        }
    }
}