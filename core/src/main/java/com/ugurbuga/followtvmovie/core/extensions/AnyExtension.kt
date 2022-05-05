package com.ugurbuga.followtvmovie.core.extensions

import android.content.Context
import com.ugurbuga.followtvmovie.core.common.CommonUtil

fun Any.getString(context: Context): String {
    return when (this) {
        is String -> {
            this
        }
        is Int -> {
            context.getString(this)
        }
        else -> {
            CommonUtil.EMPTY_STRING
        }
    }
}
