package com.ugurbuga.followtvmovie.data.repository.favorites

import com.ugurbuga.followtvmovie.data.BaseRepository
import com.ugurbuga.followtvmovie.core.common.ApiState
import com.ugurbuga.followtvmovie.data.dao.FavoritesDao
import com.ugurbuga.followtvmovie.data.model.PosterItemModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow


class FavoritesRepositoryImpl @Inject constructor(
    private val favoritesDao: FavoritesDao
) :
    FavoritesRepository, BaseRepository() {

    override fun insert(note: PosterItemModel): Flow<ApiState<Unit>> {
        return onRoomCall { favoritesDao.insert(note) }
    }

    override fun delete(id: String): Flow<ApiState<Unit>> {
        return onRoomCall { favoritesDao.delete(id) }
    }

    override fun getFavorites(
        mediaType: String,
        isWatched: Boolean
    ): Flow<ApiState<MutableList<PosterItemModel>>> {
        return onRoomFlowCall(favoritesDao.getFavorites(mediaType, isWatched))
    }

    override fun getFavorite(mediaType: String, id: String): Flow<ApiState<PosterItemModel?>> {
        return onRoomFlowCall(favoritesDao.getFavorite(mediaType, id))
    }

    override fun getSoonMovies(mediaType: String): Flow<ApiState<MutableList<PosterItemModel>>> {
        return onRoomFlowCall(favoritesDao.getSoonMovies(mediaType))
    }

    override suspend fun update(note: PosterItemModel) {
        favoritesDao.update(note)
    }
}
