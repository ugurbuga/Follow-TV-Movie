package com.ugurbuga.followtvmovie.extensions

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.ugurbuga.followtvmovie.common.ImageManager

@BindingAdapter("imageUrl")
fun AppCompatImageView.setImageUrl(url: String?) {
    ImageManager.setImageUrl(url, this)
}

@BindingAdapter("android:src")
fun AppCompatImageView.setImageFromResource(resource: Int) {
    setImageResource(resource)
}

@BindingAdapter("tintColor")
fun AppCompatImageView.setTintColor(color: String) {
    setColorFilter(Color.parseColor(color), PorterDuff.Mode.SRC_ATOP)
}
