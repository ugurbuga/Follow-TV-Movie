package com.ugurbuga.followtvmovie.domain.moviedetail.usecase

import com.ugurbuga.followtvmovie.base.FTMUseCase
import com.ugurbuga.followtvmovie.common.Resource
import com.ugurbuga.followtvmovie.common.map
import com.ugurbuga.followtvmovie.domain.moviedetail.image.ImageUIModel
import com.ugurbuga.followtvmovie.domain.moviedetail.mapper.MovieMapper
import com.ugurbuga.followtvmovie.repository.movie.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetImagesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieMapper: MovieMapper
) : FTMUseCase<GetImagesUseCase.ImageParams, ArrayList<ImageUIModel>>() {

    data class ImageParams(val movieId: Int)

    override fun execute(params: ImageParams): Flow<Resource<ArrayList<ImageUIModel>>> {
        return movieRepository.getImages(params.movieId).map {
            it.map { imageResponse ->
                movieMapper.toImageList(imageResponse)
            }
        }
    }
}