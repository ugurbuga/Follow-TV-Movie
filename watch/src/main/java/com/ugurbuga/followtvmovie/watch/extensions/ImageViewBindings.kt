package com.ugurbuga.followtvmovie.watch.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: Any?) {
    context.resources.configuration.isScreenRound
    if (context.resources.configuration.isScreenRound) {
        Glide.with(this).load(url).circleCrop().into(this)
    } else {
        Glide.with(this).load(url).centerCrop().into(this)
    }
}
