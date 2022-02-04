package com.ugurbuga.followtvmovie.base.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import kotlinx.coroutines.flow.Flow

// annotation for dao class.
@Dao
interface FavoritesDao {

    // below is the insert method for
    // adding a new entry to our database.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: PosterItemUIModel)

    // below is the delete method
    // for deleting our note.
    @Delete
    suspend fun delete(note: PosterItemUIModel)

    // below is the method to read all the notes
    // from our database we have specified the query for it.
    // inside the query we are arranging it in ascending
    // order on below line and we are specifying
    // the table name from which
    // we have to get the data.
    @Query("Select * from favoritesTable where type=:listType order by id")
    fun getFavorites(listType: String): Flow<MutableList<PosterItemUIModel>>

    // below method is use to update the note.
    @Update
    suspend fun update(note: PosterItemUIModel)

}