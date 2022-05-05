package com.ugurbuga.followtvmovie.domain.poster.model

import com.ugurbuga.followtvmovie.core.adapter.ListAdapterItem
import com.ugurbuga.followtvmovie.core.common.CommonUtil

data class LoadingUIModel(val id: String = CommonUtil.EMPTY_STRING) : ListAdapterItem
