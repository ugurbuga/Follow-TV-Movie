package com.ugurbuga.followtvmovie.domain.moviedetail.model.review

import android.text.Spanned
import com.ugurbuga.followtvmovie.base.adapter.ListAdapterItem
import com.ugurbuga.followtvmovie.extensions.fromHtml

data class ReviewUIModel(
    val author: String,
    val content: String,
    val createdAt: String,
    val id: String,
) : ListAdapterItem {

    fun getHtmlContent(): Spanned {
            return content.fromHtml()
    }
}