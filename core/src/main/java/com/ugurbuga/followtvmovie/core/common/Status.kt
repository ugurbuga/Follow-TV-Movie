package com.ugurbuga.followtvmovie.core.common

sealed class Status {

    object Success : Status()

    data class Error(val exception: Throwable? = null, val errorMessage: String? = null) : Status()

    object Loading : Status()

}