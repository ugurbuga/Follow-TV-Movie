package com.ugurbuga.followtvmovie.extensions

import android.content.Context
import android.text.Spanned
import androidx.core.text.HtmlCompat
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

fun String.fromHtml(): Spanned {
    return HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)
}
