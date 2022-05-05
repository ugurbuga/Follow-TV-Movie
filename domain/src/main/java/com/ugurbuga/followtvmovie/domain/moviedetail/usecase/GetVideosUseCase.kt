package com.ugurbuga.followtvmovie.domain.moviedetail.usecase

import com.ugurbuga.followtvmovie.core.base.UseCase
import com.ugurbuga.followtvmovie.core.common.ApiState
import com.ugurbuga.followtvmovie.core.common.map
import com.ugurbuga.followtvmovie.data.repository.common.CommonRepository
import com.ugurbuga.followtvmovie.domain.moviedetail.mapper.VideoMapper
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.VideoUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetVideosUseCase @Inject constructor(
    private val commonRepository: CommonRepository,
    private val videoMapper: VideoMapper
) : UseCase<GetVideosUseCase.VideoParams, ArrayList<VideoUIModel>>() {

    data class VideoParams(val id: String, val mediaType: String)

    override fun execute(params: VideoParams): Flow<ApiState<ArrayList<VideoUIModel>>> {
        return commonRepository.getVideos(params.id, params.mediaType).map {
            it.map { videosResponse ->
                videoMapper.toVideoList(videosResponse)
            }
        }
    }
}