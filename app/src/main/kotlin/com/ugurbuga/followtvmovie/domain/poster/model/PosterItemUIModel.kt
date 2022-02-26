package com.ugurbuga.followtvmovie.domain.poster.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ugurbuga.followtvmovie.base.adapter.ListAdapterItem

@Entity(tableName = "favoritesTable")
data class PosterItemUIModel(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "posterPath")
    val posterPath: String,

    @ColumnInfo(name = "mediaType")
    val mediaType: String
) : ListAdapterItem