package com.ugurbuga.followtvmovie.core.extensions

import androidx.annotation.DimenRes
import androidx.databinding.ViewDataBinding

fun <VB : ViewDataBinding> VB.dimenToPx(@DimenRes dimen: Int): Int {
    return root.dimenToPx(dimen)
}
