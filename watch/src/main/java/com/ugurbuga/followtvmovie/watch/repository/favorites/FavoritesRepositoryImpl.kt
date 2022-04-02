package com.ugurbuga.followtvmovie.watch.repository.favorites

import com.ugurbuga.followtvmovie.watch.dao.FavoritesDao
import com.ugurbuga.followtvmovie.watch.ui.detail.PosterItemUIModel
import com.ugurbuga.followtvmovie.watch.base.FTMBaseRepository
import com.ugurbuga.followtvmovie.watch.util.Resource
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

    override fun getSoonMovies(): Flow<Resource<MutableList<PosterItemUIModel>>> {
        return onRoomFlowCall(favoritesDao.getSoonMovies())
    }

    override suspend fun update(note: PosterItemUIModel) {
        favoritesDao.update(note)
    }
}
