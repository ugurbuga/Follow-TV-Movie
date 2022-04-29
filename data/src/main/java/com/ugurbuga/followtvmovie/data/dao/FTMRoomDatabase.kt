package com.ugurbuga.followtvmovie.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ugurbuga.followtvmovie.data.model.PosterItemModel

@Database(entities = [PosterItemModel::class], version = 2, exportSchema = false)
abstract class FTMRoomDatabase : RoomDatabase() {

    abstract fun getFavoritesDao(): FavoritesDao
}