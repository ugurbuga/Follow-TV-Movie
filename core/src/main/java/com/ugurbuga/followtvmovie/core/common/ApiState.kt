package com.ugurbuga.followtvmovie.core.common


// references :
// https://developer.android.com/jetpack/docs/guide#addendum


sealed class ApiState<out T> {
    class Success<T>(val data: T) : ApiState<T>()
    class Error(val message: Any, val code: Int) : ApiState<Nothing>()
    object Loading : ApiState<Nothing>()
}

inline fun <T, R> ApiState<T>.map(transform: (T) -> R): ApiState<R> {
    return when (this) {
        is ApiState.Success -> ApiState.Success(transform(data))
        is ApiState.Error -> ApiState.Error(message, code)
        is ApiState.Loading -> ApiState.Loading
    }
}