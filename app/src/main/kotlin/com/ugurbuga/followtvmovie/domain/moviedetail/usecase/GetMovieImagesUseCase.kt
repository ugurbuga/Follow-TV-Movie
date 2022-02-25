package com.ugurbuga.followtvmovie.domain.moviedetail.usecase

import com.ugurbuga.followtvmovie.base.FTMUseCase
import com.ugurbuga.followtvmovie.common.Resource
import com.ugurbuga.followtvmovie.common.map
import com.ugurbuga.followtvmovie.domain.image.ImageMapper
import com.ugurbuga.followtvmovie.domain.moviedetail.image.ImageUIModel
import com.ugurbuga.followtvmovie.repository.movie.MovieRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetMovieImagesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val imageMapper: ImageMapper,
) : FTMUseCase<GetMovieImagesUseCase.MovieImageParams, ArrayList<ImageUIModel>>() {

    data class MovieImageParams(val movieId: String)

    override fun execute(params: MovieImageParams): Flow<Resource<ArrayList<ImageUIModel>>> {
        return movieRepository.getMovieImages(params.movieId).map {
            it.map { imageResponse ->
                imageMapper.toMovieImageList(imageResponse)
            }
        }
    }
}