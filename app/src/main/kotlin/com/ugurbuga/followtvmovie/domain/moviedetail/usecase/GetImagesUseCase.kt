package com.ugurbuga.followtvmovie.domain.moviedetail.usecase

import com.ugurbuga.followtvmovie.base.FTMUseCase
import com.ugurbuga.followtvmovie.common.ApiState
import com.ugurbuga.followtvmovie.common.map
import com.ugurbuga.followtvmovie.domain.image.ImageMapper
import com.ugurbuga.followtvmovie.domain.moviedetail.image.ImageUIModel
import com.ugurbuga.followtvmovie.repository.common.CommonRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetImagesUseCase @Inject constructor(
    private val commonRepository: CommonRepository,
    private val imageMapper: ImageMapper,
) : FTMUseCase<GetImagesUseCase.ImageParams, ArrayList<ImageUIModel>>() {

    data class ImageParams(val id: String, val mediaType: String)

    override fun execute(params: ImageParams): Flow<ApiState<ArrayList<ImageUIModel>>> {
        return commonRepository.getImages(params.id, params.mediaType).map {
            it.map { imageResponse ->
                imageMapper.toMovieImageList(imageResponse)
            }
        }
    }
}