package com.ugurbuga.followtvmovie.domain.image.usecase

import com.ugurbuga.followtvmovie.core.base.UseCase
import com.ugurbuga.followtvmovie.core.common.ApiState
import com.ugurbuga.followtvmovie.core.common.map
import com.ugurbuga.followtvmovie.data.repository.common.CommonRepository
import com.ugurbuga.followtvmovie.domain.image.mapper.ImageMapper
import com.ugurbuga.followtvmovie.domain.image.model.ImageUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetImagesUseCase @Inject constructor(
    private val commonRepository: CommonRepository,
    private val imageMapper: ImageMapper,
) : UseCase<GetImagesUseCase.ImageParams, ArrayList<ImageUIModel>>() {

    data class ImageParams(val id: String, val mediaType: String)

    override fun execute(params: ImageParams): Flow<ApiState<ArrayList<ImageUIModel>>> {
        return commonRepository.getImages(params.id, params.mediaType).map {
            it.map { imageResponse ->
                imageMapper.toMovieImageList(imageResponse)
            }
        }
    }
}