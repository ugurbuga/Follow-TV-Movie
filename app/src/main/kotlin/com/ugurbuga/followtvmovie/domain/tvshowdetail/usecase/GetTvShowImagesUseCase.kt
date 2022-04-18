package com.ugurbuga.followtvmovie.domain.tvshowdetail.usecase

import com.ugurbuga.followtvmovie.base.FTMUseCase
import com.ugurbuga.followtvmovie.common.ApiState
import com.ugurbuga.followtvmovie.common.map
import com.ugurbuga.followtvmovie.domain.image.ImageMapper
import com.ugurbuga.followtvmovie.domain.moviedetail.image.ImageUIModel
import com.ugurbuga.followtvmovie.repository.tvshow.TvShowRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTvShowImagesUseCase @Inject constructor(
    private val txShowRepository: TvShowRepository,
    private val imageMapper: ImageMapper,
) : FTMUseCase<GetTvShowImagesUseCase.TvShowImageParams, ArrayList<ImageUIModel>>() {

    data class TvShowImageParams(val tvShowId: String)

    override fun execute(params: TvShowImageParams): Flow<ApiState<ArrayList<ImageUIModel>>> {
        return txShowRepository.getTvShowImages(params.tvShowId).map {
            it.map { imageResponse ->
                imageMapper.toMovieImageList(imageResponse)
            }
        }
    }
}