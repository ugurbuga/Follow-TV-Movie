package com.ugurbuga.followtvmovie.watch.ui.detail

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String?) {
    Glide.with(this).load(url).circleCrop().into(this)
}
