package com.ugurbuga.followtvmovie.extensions

import com.ugurbuga.followtvmovie.common.ApiState
import com.ugurbuga.followtvmovie.common.Status
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

fun <T> Flow<ApiState<T>>.doOnSuccess(action: suspend (T) -> Unit): Flow<ApiState<T>> =
    transform { value ->
        if (value is ApiState.Success) {
            action(value.data)
        }
        return@transform emit(value)
    }

fun <T> Flow<ApiState<T>>.doOnError(action: suspend (Throwable) -> Unit): Flow<ApiState<T>> =
    transform { value ->
        if (value is ApiState.Error) {
            action(value.exception)
        }
        return@transform emit(value)
    }

fun <T> Flow<ApiState<T>>.doOnStatusChanged(action: suspend (Status) -> Unit): Flow<ApiState<T>> =
    transform { value ->
        when (value) {
            is ApiState.Success -> action(Status.Success)
            is ApiState.Error -> action(Status.Error(value.exception))
            ApiState.Loading -> action(Status.Loading)
        }
        return@transform emit(value)
    }

fun <T> Flow<ApiState<T>>.doOnLoading(action: suspend () -> Unit): Flow<ApiState<T>> =
    transform { value ->
        if (value is ApiState.Loading) {
            action()
        }
        return@transform emit(value)
    }

