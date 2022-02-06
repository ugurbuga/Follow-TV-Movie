package com.ugurbuga.followtvmovie.repository.favorites

import com.ugurbuga.followtvmovie.base.FTMBaseRepository
import com.ugurbuga.followtvmovie.base.dao.FavoritesDao
import com.ugurbuga.followtvmovie.common.Resource
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class FavoritesRepositoryImpl @Inject constructor(private val favoritesDao: FavoritesDao) :
    FavoritesRepository, FTMBaseRepository() {

    override fun insert(note: PosterItemUIModel): Flow<Resource<Unit>> {
        return onRoomCall { favoritesDao.insert(note) }
    }

    override suspend fun delete(note: PosterItemUIModel) {
        favoritesDao.delete(note)
    }

    override fun getFavorites(listType: String): Flow<Resource<MutableList<PosterItemUIModel>>> {
        return  favoritesDao.getFavorites(listType).map {
            Resource.Success(it)
        }
    }

    override suspend fun update(note: PosterItemUIModel) {
        favoritesDao.update(note)
    }
}
