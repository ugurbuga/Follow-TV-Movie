package com.ugurbuga.followtvmovie.domain.moviedetail.usecase

import com.ugurbuga.followtvmovie.core.base.UseCase
import com.ugurbuga.followtvmovie.core.common.ApiState
import com.ugurbuga.followtvmovie.core.common.map
import com.ugurbuga.followtvmovie.data.repository.favorites.FavoritesRepository
import com.ugurbuga.followtvmovie.domain.poster.mapper.PosterMapper
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSoonMoviesUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
    private val posterMapper: PosterMapper
) :
    UseCase<GetSoonMoviesUseCase.SoonMovies, MutableList<PosterItemUIModel>>() {

    data class SoonMovies(val mediaType: String)

    override fun execute(params: SoonMovies): Flow<ApiState<MutableList<PosterItemUIModel>>> {
        return favoritesRepository.getSoonMovies(params.mediaType)
            .map { it.map { data -> posterMapper.toPosterItemUIModelList(data) } }
    }
}