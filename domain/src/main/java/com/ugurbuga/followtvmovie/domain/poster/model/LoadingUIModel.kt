package com.ugurbuga.followtvmovie.domain.poster.model

import com.ugurbuga.followtvmovie.core.adapter.ListAdapterItem
import com.ugurbuga.followtvmovie.core.common.Util

data class LoadingUIModel(val id: String = Util.EMPTY_STRING) : ListAdapterItem
