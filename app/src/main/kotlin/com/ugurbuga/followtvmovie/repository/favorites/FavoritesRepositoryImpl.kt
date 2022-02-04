package com.ugurbuga.followtvmovie.repository.favorites

import com.ugurbuga.followtvmovie.base.FTMBaseRepository
import com.ugurbuga.followtvmovie.base.dao.FavoritesDao
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class FavoritesRepositoryImpl @Inject constructor(private val favoritesDao: FavoritesDao) :
    FavoritesRepository, FTMBaseRepository() {


    override suspend fun insert(note: PosterItemUIModel) {
        favoritesDao.insert(note)
    }


    override suspend fun delete(note: PosterItemUIModel) {
        favoritesDao.delete(note)
    }


    override fun getFavorites(listType: String): Flow<MutableList<PosterItemUIModel>> {
        return favoritesDao.getFavorites(listType)
    }

    override suspend fun update(note: PosterItemUIModel) {
        favoritesDao.update(note)
    }
}
