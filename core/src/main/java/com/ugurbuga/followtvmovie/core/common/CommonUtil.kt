package com.ugurbuga.followtvmovie.core.common

import android.content.Context
import android.graphics.Typeface
import com.ugurbuga.followtvmovie.core.extensions.font
import com.ugurbuga.followtvmovie.core.extensions.orZero
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


object CommonUtil {

    const val EMPTY_STRING = ""
    const val INVALID_INDEX = -1
    const val ZERO = 0
    const val ZERO_LONG = 0L
    private const val DATE_PATTERN = "yyyy-MM-dd"

    fun getTypefaceFromFontRes(context: Context, font: Int): Typeface? {
        return context.font(font)
    }

    fun getDateLong(date: String?): Long {
        return if (!date.isNullOrEmpty()) {
            val sdf = SimpleDateFormat(DATE_PATTERN, Locale.getDefault())
            try {
                sdf.parse(date)?.time.orZero()
            } catch (e: Exception) {
                ZERO_LONG
            }
        } else {
            ZERO_LONG
        }

    }

    fun getDateString(millisecond: Long): String {
        val sdf = SimpleDateFormat(DATE_PATTERN, Locale.getDefault())

        return sdf.format(Date(millisecond)).toString()
    }
}
