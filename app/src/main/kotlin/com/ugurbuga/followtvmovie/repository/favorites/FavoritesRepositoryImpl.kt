package com.ugurbuga.followtvmovie.repository.favorites

import com.ugurbuga.followtvmovie.base.FTMBaseRepository
import com.ugurbuga.followtvmovie.base.dao.FavoritesDao
import com.ugurbuga.followtvmovie.common.Resource
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class FavoritesRepositoryImpl @Inject constructor(private val favoritesDao: FavoritesDao) :
    FavoritesRepository, FTMBaseRepository() {

    override fun insert(note: PosterItemUIModel): Flow<Resource<Unit>> {
        return onRoomCall { favoritesDao.insert(note) }
    }

    override fun delete(id: Int): Flow<Resource<Unit>> {
        return onRoomCall { favoritesDao.delete(id) }
    }

    override fun getFavorites(listType: String): Flow<Resource<MutableList<PosterItemUIModel>>> {
        return onRoomFlowCall(favoritesDao.getFavorites(listType))
    }

    override fun getFavorite(listType: String, id: Int): Flow<Resource<PosterItemUIModel?>> {
        return onRoomFlowCall(favoritesDao.getFavorite(listType, id))
    }

    override suspend fun update(note: PosterItemUIModel) {
        favoritesDao.update(note)
    }
}
