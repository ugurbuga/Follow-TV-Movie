package com.ugurbuga.followtvmovie.extensions

import android.content.Context
import android.graphics.Paint
import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.FontRes
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.res.ResourcesCompat
import androidx.core.text.HtmlCompat
import com.ugurbuga.followtvmovie.common.CustomTypefaceSpan

fun View.hideKeyboard() {
    (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
        .hideSoftInputFromWindow(windowToken, 0)
}

fun View.showKeyboard() {
    (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
        .showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun TextView.setTypeface(@FontRes font: Int) {
    typeface = getTypefaceFromFontRes(context, font)
}

private fun getTypefaceFromFontRes(context: Context, font: Int): Typeface? {
    return ResourcesCompat.getFont(context, font)
}

fun TextView.setSpannableTextFromResource(
    title: String,
    @FontRes fontRes: Int,
    start: Int,
    end: Int,
    flag: Int
) {
    val spannableString = SpannableString(title)
    val newTypeface = getTypefaceFromFontRes(context, fontRes)

    newTypeface?.let {
        spannableString.setSpan(CustomTypefaceSpan(newTypeface), start, end, flag)
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

fun String.fromHtml(): Spanned {
    return HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)
}

internal fun View?.findSuitableParent(): ViewGroup? {
    var view = this
    var fallback: ViewGroup? = null

    do {
        if (view is CoordinatorLayout) {
            return view
        } else if (view is FrameLayout) {
            if (view.id == android.R.id.content) {
                return view
            } else {
                fallback = view
            }
        }

        if (view != null) {
            val parent = view.parent
            view = if (parent is View) parent else null
        }
    } while (view != null)

    return fallback
}

