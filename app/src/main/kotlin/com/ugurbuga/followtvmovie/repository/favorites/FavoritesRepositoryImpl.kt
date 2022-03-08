package com.ugurbuga.followtvmovie.repository.favorites

import com.ugurbuga.followtvmovie.base.FTMBaseRepository
import com.ugurbuga.followtvmovie.base.dao.FavoritesDao
import com.ugurbuga.followtvmovie.common.Resource
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow


class FavoritesRepositoryImpl @Inject constructor(
    private val favoritesDao: FavoritesDao
) :
    FavoritesRepository, FTMBaseRepository() {

    override fun insert(note: PosterItemUIModel): Flow<Resource<Unit>> {
        return onRoomCall { favoritesDao.insert(note) }
    }

    override fun delete(id: String): Flow<Resource<Unit>> {
        return onRoomCall { favoritesDao.delete(id) }
    }

    override fun getFavorites(
        mediaType: String,
        isWatched: Boolean
    ): Flow<Resource<MutableList<PosterItemUIModel>>> {
        return onRoomFlowCall(favoritesDao.getFavorites(mediaType, isWatched))
    }

    override fun getFavorite(mediaType: String, id: String): Flow<Resource<PosterItemUIModel?>> {
        return onRoomFlowCall(favoritesDao.getFavorite(mediaType, id))
    }

    override suspend fun update(note: PosterItemUIModel) {
        favoritesDao.update(note)
    }
}
