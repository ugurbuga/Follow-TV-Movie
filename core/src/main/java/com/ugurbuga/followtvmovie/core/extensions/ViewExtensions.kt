package com.ugurbuga.followtvmovie.core.extensions

import android.content.res.ColorStateList
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes

fun View.color(@ColorRes resource: Int): Int {
    return context.color(resource)
}

fun View.colorStateList(@ColorRes resource: Int): ColorStateList? {
    return context.colorStateList(resource)
}

fun View.drawable(@DrawableRes resource: Int): Drawable? {
    return context.drawable(resource)
}

fun View.font(@FontRes resource: Int): Typeface? {
    return context.font(resource)
}

fun View.dimenToPx(@DimenRes dimen: Int): Int {
    return context.dimenToPx(dimen)
}