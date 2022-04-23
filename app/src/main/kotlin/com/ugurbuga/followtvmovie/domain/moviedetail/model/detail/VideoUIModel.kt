package com.ugurbuga.followtvmovie.domain.moviedetail.model.detail

import com.ugurbuga.followtvmovie.base.adapter.ListAdapterItem

data class VideoUIModel(
    val key: String,
    val name: String
) : ListAdapterItem {

    fun getVideoUrl(): String {
        return "https://img.youtube.com/vi/${key}/0.jpg"
    }
}