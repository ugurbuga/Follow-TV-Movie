package com.ugurbuga.followtvmovie.domain.poster.model

import android.content.Context
import com.ugurbuga.followtvmovie.base.adapter.ListAdapterItem
import com.ugurbuga.followtvmovie.common.Util
import com.ugurbuga.followtvmovie.extensions.getString

data class EmptyUIModel(
    val id: String = Util.EMPTY_STRING,
    val message: Any
) : ListAdapterItem {

    fun getEmptyMessage(context: Context): String {
        return message.getString(context)
    }
}
