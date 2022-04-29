package com.ugurbuga.followtvmovie.data.dao

import androidx.room.*
import com.ugurbuga.followtvmovie.data.model.MediaType
import com.ugurbuga.followtvmovie.data.model.PosterItemModel
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: PosterItemModel)

    @Query("Delete from favoritesTable WHERE id = :id")
    suspend fun delete(id: String)

    @Query("Select * from favoritesTable where mediaType=:mediaType AND isWatched=:isWatched order by releaseDateLong DESC")
    fun getFavorites(mediaType: String, isWatched: Boolean): Flow<MutableList<PosterItemModel>>

    @Query("Select * from favoritesTable where mediaType=:mediaType AND id=:id")
    fun getFavorite(mediaType: String, id: String): Flow<PosterItemModel?>

    @Update
    suspend fun update(note: PosterItemModel)

    @Query("Select * from favoritesTable where mediaType=:mediaType AND isWatched=:isWatched AND releaseDateLong > :date order by releaseDateLong DESC")
    fun getFutureMovies(
        mediaType: String = MediaType.MOVIE,
        isWatched: Boolean = false,
        date: Long = Calendar.getInstance().time.time
    ): MutableList<PosterItemModel>

    @Query("Select * from favoritesTable where mediaType=:mediaType AND isWatched=:isWatched AND releaseDateLong > :date order by releaseDateLong ASC")
    fun getSoonMovies(
        mediaType: String = MediaType.MOVIE,
        isWatched: Boolean = false,
        date: Long = Calendar.getInstance().time.time
    ): Flow<MutableList<PosterItemModel>>

}