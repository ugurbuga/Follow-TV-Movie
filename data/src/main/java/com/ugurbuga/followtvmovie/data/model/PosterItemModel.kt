package com.ugurbuga.followtvmovie.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoritesTable")
data class PosterItemModel(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "posterPath")
    val posterPath: String,

    @ColumnInfo(name = "mediaType")
    val mediaType: String,

    @ColumnInfo(name = "releaseDate")
    val releaseDate: String,

    @ColumnInfo(name = "releaseDateLong")
    val releaseDateLong: Long,

    @ColumnInfo(name = "isWatched")
    val isWatched: Boolean = false
)
