package com.ugurbuga.followtvmovie.core.common

sealed class Status {

    object Success : Status()

    data class Error(val message: Any, val code: Int) : Status()

    object Loading : Status()

}