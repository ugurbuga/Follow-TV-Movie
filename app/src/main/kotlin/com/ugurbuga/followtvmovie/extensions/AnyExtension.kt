package com.ugurbuga.followtvmovie.extensions

import android.content.Context
import com.ugurbuga.followtvmovie.common.Util

fun Any.getString(context: Context): String {
    return when (this) {
        is String -> {
            this
        }
        is Int -> {
            context.getString(this)
        }
        else -> {
            Util.EMPTY_STRING
        }
    }
}
