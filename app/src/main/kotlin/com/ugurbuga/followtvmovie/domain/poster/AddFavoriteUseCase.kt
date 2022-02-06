package com.ugurbuga.followtvmovie.domain.poster

import com.ugurbuga.followtvmovie.base.FTMResourceUseCase
import com.ugurbuga.followtvmovie.common.Resource
import com.ugurbuga.followtvmovie.domain.moviedetail.model.MovieDetailUIModel
import com.ugurbuga.followtvmovie.domain.poster.mapper.PosterMapper
import com.ugurbuga.followtvmovie.repository.favorites.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddFavoriteUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
    private val posterMapper: PosterMapper
) :
    FTMResourceUseCase<AddFavoriteUseCase.AddFavoriteParams, Unit>() {

    data class AddFavoriteParams(val movieDetail: MovieDetailUIModel)

    override fun execute(params: AddFavoriteParams): Flow<Resource<Unit>> {
        return favoritesRepository.insert(posterMapper.toPosterUIModel(params.movieDetail))
    }
}