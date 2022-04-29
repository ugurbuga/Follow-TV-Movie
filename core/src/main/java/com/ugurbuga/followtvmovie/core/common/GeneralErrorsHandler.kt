package com.ugurbuga.followtvmovie.core.common

import retrofit2.HttpException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class GeneralErrorsHandler(
    private var onErrorMessage: ((Any, Int) -> Unit?)? = null,
    throwable: Throwable
) {

    private var errorBody: ErrorBody? = null

    init {
        if (isNetworkError(throwable)) {
            onErrorMessage?.invoke("R.string.generic_error_message", 400)
        } else if (throwable is HttpException) {
            errorBody = ErrorBody.parseError(throwable.response())
            if (errorBody != null) {
                handleError(errorBody!!)
            }
        } else {
            throwable.message?.let {
                onErrorMessage?.invoke(it, 0)
            }
        }
    }

    private fun handleError(errorBody: ErrorBody) {
        if (errorBody.code != ErrorBody.UNKNOWN_ERROR) {
            var errorMessage: String? = null
            errorBody.errors?.forEach {
                if (errorMessage == null) {
                    errorMessage = it
                } else {
                    errorMessage += "\n" + it
                }
            }

            errorMessage?.let {
                onErrorMessage?.invoke(it, errorBody.code)
            } ?: run { onErrorMessage?.invoke("R.string.generic_error_message", errorBody.code) }
        }
    }

    private fun isNetworkError(throwable: Throwable): Boolean {
        return throwable is SocketException ||
                throwable is UnknownHostException ||
                throwable is SocketTimeoutException
    }
}
