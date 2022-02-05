package com.ugurbuga.followtvmovie.common

import android.content.Context
import android.graphics.Typeface
import androidx.core.content.res.ResourcesCompat
import com.ugurbuga.followtvmovie.data.api.ApiConstants

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

    fun getPosterPath(posterPath: String?, backdropPath: String?): String {
        posterPath?.let {
            return ApiConstants.BASE_IMAGE_URL + it
        }

        backdropPath?.let {
            return ApiConstants.BASE_IMAGE_URL + it
        }

        return EMPTY_STRING
    }

    fun getTypefaceFromFontRes(context: Context, font: Int): Typeface? {
        return ResourcesCompat.getFont(context, font)
    }
}
