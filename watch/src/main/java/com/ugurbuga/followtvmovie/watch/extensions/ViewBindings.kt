package com.ugurbuga.followtvmovie.watch.extensions

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("isVisible")
fun isVisible(view: View, isVisible: Boolean) {
    view.isVisible = isVisible
}
