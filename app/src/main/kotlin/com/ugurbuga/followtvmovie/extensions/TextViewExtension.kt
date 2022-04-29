package com.ugurbuga.followtvmovie.extensions

import android.graphics.Paint
import android.text.SpannableString
import android.widget.TextView
import androidx.annotation.FontRes
import com.ugurbuga.followtvmovie.core.common.CustomTypefaceSpan
import com.ugurbuga.followtvmovie.core.common.Util


fun TextView.setTypeface(@FontRes font: Int) {
    typeface = Util.getTypefaceFromFontRes(context, font)
}

fun TextView.setSpannableTextFromResource(
    title: String,
    @FontRes fontRes: Int,
    start: Int,
    end: Int,
    flag: Int
) {
    val spannableString = SpannableString(title)
    val newTypeface = Util.getTypefaceFromFontRes(context, fontRes)

    newTypeface?.let {
        spannableString.setSpan(
            CustomTypefaceSpan(
                newTypeface
            ), start, end, flag)
    }
    text = spannableString
}

fun TextView.strike(isShow: Boolean) {
    paintFlags = if (isShow) {
        paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    } else {
        paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}

fun TextView.underline(isShow: Boolean) {
    paintFlags = if (isShow) {
        paintFlags or Paint.UNDERLINE_TEXT_FLAG
    } else {
        paintFlags and Paint.UNDERLINE_TEXT_FLAG.inv()
    }
}