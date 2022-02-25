package com.ugurbuga.followtvmovie.common

import android.content.Context
import android.graphics.Typeface
import androidx.core.content.res.ResourcesCompat
import java.text.SimpleDateFormat
import java.util.Date
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

    fun getDate(dateString: String): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date: Date? = dateFormat.parse(dateString)
        return date?.let { dateFormat.format(date) } ?: kotlin.run { EMPTY_STRING }
    }
}
