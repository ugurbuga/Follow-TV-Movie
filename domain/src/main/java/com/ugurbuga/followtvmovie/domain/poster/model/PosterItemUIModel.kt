package com.ugurbuga.followtvmovie.domain.poster.model

import android.content.Context
import com.ugurbuga.followtvmovie.core.adapter.ListAdapterItem
import java.util.*
import java.util.concurrent.TimeUnit


data class PosterItemUIModel(

    val id: String,


    val name: String,


    val posterPath: String,


    val mediaType: String,


    val releaseDate: String,


    val releaseDateLong: Long,


    val isWatched: Boolean = false
) : ListAdapterItem {

    fun getDateCount(context: Context): String {
        val milliseconds = releaseDateLong.minus(Calendar.getInstance().time.time)
        val days = TimeUnit.MILLISECONDS.toDays(milliseconds).toInt().plus(1)
        return days.toString()
        /*return if (days > 1) {
            //FIXME:
            //context.getString(R.string.x_days, days)
            days
        } else {
            context.getString(R.string.one_day)
        }*/
    }
}