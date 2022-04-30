package com.ugurbuga.followtvmovie.core.extensions

import android.content.Context
import com.ugurbuga.followtvmovie.core.common.Util

fun Any.getString(context: Context): String {
    return when (this) {
        is String -> {
            this
        }
        is Int -> {
            context.getString(this)
        }
        else -> {
            com.ugurbuga.followtvmovie.core.common.Util.EMPTY_STRING
        }
    }
}
