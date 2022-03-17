package com.ugurbuga.followtvmovie.ui.soon

import com.ugurbuga.followtvmovie.base.FTMUseCase
import com.ugurbuga.followtvmovie.common.Resource
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import com.ugurbuga.followtvmovie.repository.favorites.FavoritesRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetSoonMoviesUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) :
    FTMUseCase<Any, MutableList<PosterItemUIModel>>() {

    override fun execute(params: Any): Flow<Resource<MutableList<PosterItemUIModel>>> {
        return favoritesRepository.getSoonMovies()
    }
}