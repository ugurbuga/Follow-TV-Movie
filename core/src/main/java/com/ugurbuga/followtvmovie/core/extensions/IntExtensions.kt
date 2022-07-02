package com.ugurbuga.followtvmovie.core.extensions

import com.ugurbuga.followtvmovie.core.common.CommonUtil

fun Int?.orInvalidIndex(): Int = this ?: CommonUtil.INVALID_INDEX

fun Int?.orZero(): Int = this ?: CommonUtil.ZERO

fun Long?.orZero(): Long = this ?: CommonUtil.ZERO_LONG