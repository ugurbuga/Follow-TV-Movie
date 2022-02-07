package com.ugurbuga.followtvmovie.base.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: PosterItemUIModel)

    @Query("Delete from favoritesTable WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("Select * from favoritesTable where type=:listType order by id")
    fun getFavorites(listType: String): Flow<MutableList<PosterItemUIModel>>

    @Query("Select * from favoritesTable where type=:listType AND id=:id")
    fun getFavorite(listType: String, id: Int): Flow<PosterItemUIModel?>

    @Update
    suspend fun update(note: PosterItemUIModel)

}