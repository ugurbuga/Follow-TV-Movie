package com.ugurbuga.followtvmovie.common

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable

object ImageManager {

    fun setImageUrl(url: String?, imageView: AppCompatImageView) {
        Glide.with(imageView).load(url).placeholder(getShimmerDrawable()).into(imageView)
    }

    private fun getShimmerDrawable(): ShimmerDrawable {
        val shimmer =
            Shimmer.ColorHighlightBuilder() // The attributes for a ShimmerDrawable is set by this builder
                .setDuration(1600) // how long the shimmering animation takes to do one full sweep
                .setBaseAlpha(0.8f) // the alpha of the underlying children
                .setHighlightAlpha(0.9f) // the shimmer alpha amount
                .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
                .setAutoStart(true)
                .build()

        // This is the placeholder for the imageView
        return ShimmerDrawable()
            .apply {
                setShimmer(shimmer)
            }
    }
}
