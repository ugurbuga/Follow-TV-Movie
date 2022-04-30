package com.ugurbuga.followtvmovie.common

import java.util.Calendar

object AppUtil {

    fun isReleased(releaseDate: Long?): Boolean {
        return if (releaseDate != null) {
            Calendar.getInstance().time.time > releaseDate
        } else {
            false
        }
    }
}