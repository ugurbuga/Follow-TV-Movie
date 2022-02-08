package com.ugurbuga.followtvmovie.domain.moviedetail.model.review

import com.ugurbuga.followtvmovie.base.adapter.ListAdapterItem

data class ReviewUIModel(
    val author: String,
    val content: String,
    val createdAt: String,
    val id: String,
) : ListAdapterItem