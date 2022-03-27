package com.ugurbuga.followtvmovie.watch.detail.model

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ugurbuga.followtvmovie.watch.R
import com.ugurbuga.followtvmovie.watch.popularlist.model.ListAdapterItem
import java.util.Calendar
import java.util.concurrent.TimeUnit

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
    val mediaType: String,

    @ColumnInfo(name = "releaseDate")
    val releaseDate: String,

    @ColumnInfo(name = "releaseDateLong")
    val releaseDateLong: Long,

    @ColumnInfo(name = "isWatched")
    val isWatched: Boolean = false
) : ListAdapterItem {

    fun getDateCount(context: Context): String {
        val milliseconds = releaseDateLong.minus(Calendar.getInstance().time.time)
        val days = TimeUnit.MILLISECONDS.toDays(milliseconds).toInt().plus(1)
        return if (days > 1) {
            context.getString(R.string.x_days, days)
        } else {
            context.getString(R.string.one_day)
        }
    }
}