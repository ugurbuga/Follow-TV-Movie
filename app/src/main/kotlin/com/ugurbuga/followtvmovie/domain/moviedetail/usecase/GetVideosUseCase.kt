package com.ugurbuga.followtvmovie.domain.moviedetail.usecase

import com.ugurbuga.followtvmovie.base.FTMUseCase
import com.ugurbuga.followtvmovie.common.ApiState
import com.ugurbuga.followtvmovie.common.map
import com.ugurbuga.followtvmovie.domain.moviedetail.mapper.VideoMapper
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.VideoUIModel
import com.ugurbuga.followtvmovie.repository.common.CommonRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetVideosUseCase @Inject constructor(
    private val commonRepository: CommonRepository,
    private val videoMapper: VideoMapper
) : FTMUseCase<GetVideosUseCase.VideoParams, ArrayList<VideoUIModel>>() {

    data class VideoParams(val id: String, val mediaType: String)

    override fun execute(params: VideoParams): Flow<ApiState<ArrayList<VideoUIModel>>> {
        return commonRepository.getVideos(params.id, params.mediaType).map {
            it.map { videosResponse ->
                videoMapper.toVideoList(videosResponse)
            }
        }
    }
}