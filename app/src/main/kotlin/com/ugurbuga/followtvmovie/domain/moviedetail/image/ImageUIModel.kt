package com.ugurbuga.followtvmovie.domain.moviedetail.image

import android.os.Parcelable
import com.ugurbuga.followtvmovie.base.adapter.ListAdapterItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageUIModel(
    val imageUrl: String,
    val aspectRatio: Double,
) : ListAdapterItem, Parcelable