package com.ugurbuga.followtvmovie.core.extensions

import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment

fun Fragment.fixUiModeIfNeeded() {
    context?.fixUiModeIfNeeded()
}

fun Fragment.dimenToPx(@DimenRes dimen: Int): Int {
    return resources.dimenToPx(dimen)
}

fun Fragment.color(@ColorRes resource: Int): Int {
    return requireContext().color(resource)
}

fun Fragment.drawable(@DrawableRes resource: Int): Drawable? {
    return requireContext().drawable(resource)
}