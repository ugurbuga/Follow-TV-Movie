package com.ugurbuga.followtvmovie.watch

import android.content.Context
import android.graphics.Typeface
import androidx.core.content.res.ResourcesCompat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


object Util {

    const val EMPTY_STRING = ""
    const val INVALID_INDEX = -1
    const val DATE_PATTERN = "yyyy-MM-dd"

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
            val sdf = SimpleDateFormat(DATE_PATTERN, Locale.getDefault())
            try {
                sdf.parse(date)?.time ?: 0
            } catch (e: java.lang.Exception) {
                0
            }
        } else {
            0
        }

    }

    fun getDateString(millisecond: Long): String {
        val sdf = SimpleDateFormat(DATE_PATTERN, Locale.getDefault())

        return sdf.format(Date(millisecond)).toString()
    }

    fun isReleased(releaseDate: Long?): Boolean {
        return if (releaseDate != null) {
            Calendar.getInstance().time.time > releaseDate
        } else {
            false
        }
    }
}
