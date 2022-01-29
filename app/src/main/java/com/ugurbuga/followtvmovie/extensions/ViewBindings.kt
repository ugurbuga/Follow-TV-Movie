package com.ugurbuga.followtvmovie.extensions

import android.view.View
import androidx.databinding.BindingAdapter

@set:BindingAdapter("visible")
var View.visible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }
