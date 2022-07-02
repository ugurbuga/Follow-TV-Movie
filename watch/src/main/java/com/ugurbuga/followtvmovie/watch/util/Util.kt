package com.ugurbuga.followtvmovie.watch.util

import java.text.SimpleDateFormat
import java.util.Locale


object Util {

    const val EMPTY_STRING = ""
    const val INVALID_INDEX = -1
    const val DATE_PATTERN = "yyyy-MM-dd"

    fun getDateLong(date: String?): Long {
        return if (date != null) {
            val sdf = SimpleDateFormat(DATE_PATTERN, Locale.getDefault())
            try {
                sdf.parse(date)?.time.orZero()
            } catch (e: java.lang.Exception) {
                0
            }
        } else {
            0
        }

    }
}
