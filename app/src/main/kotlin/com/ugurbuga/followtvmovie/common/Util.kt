package com.ugurbuga.followtvmovie.common

import android.content.Context
import android.graphics.Typeface
import androidx.core.content.res.ResourcesCompat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


object Util {

    const val EMPTY_STRING = ""
    const val INVALID_INDEX = -1

    fun canPagingAvailable(
        isCanLoadNewItem: Boolean,
        visibleItemCount: Int,
        firstVisibleItemPosition: Int,
        totalItemCount: Int
    ): Boolean {
        return isCanLoadNewItem && visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0
    }

    fun getTypefaceFromFontRes(context: Context, font: Int): Typeface? {
        return ResourcesCompat.getFont(context, font)
    }

    fun getDateLong(date: String?): Long {
        return if (date != null) {
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            sdf.parse(date)?.time ?: 0
        } else {
            0
        }

    }

    fun isReleased(releaseDate: Long?): Boolean {
        return if (releaseDate != null) {
            Calendar.getInstance().time.time > releaseDate
        } else {
            false
        }
    }
}
