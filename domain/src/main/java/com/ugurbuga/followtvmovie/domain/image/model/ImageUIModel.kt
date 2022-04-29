package com.ugurbuga.followtvmovie.domain.image.model

import android.os.Parcelable
import com.ugurbuga.followtvmovie.core.adapter.ListAdapterItem
import kotlinx.parcelize.Parcelize


@Parcelize
data class ImageUIModel(
    val imageUrl: String,
    val aspectRatio: Double,
) : ListAdapterItem, Parcelable