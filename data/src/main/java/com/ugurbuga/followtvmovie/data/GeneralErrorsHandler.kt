package com.ugurbuga.followtvmovie.data

import com.ugurbuga.followtvmovie.core.R
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import retrofit2.HttpException

object GeneralErrorsHandler {

    private var errorBody: ErrorBody? = null

    fun convertError(throwable: Throwable): Pair<Any, Int> {
        return if (isNetworkError(throwable)) {
            Pair(R.string.generic_error_message, 400)
        } else if (throwable is HttpException) {
            errorBody = ErrorBody.parseError(throwable.response())
            if (errorBody != null) {
                handleError(errorBody!!)
            } else {
                Pair(R.string.generic_error_message, 400)
            }
        } else {
            throwable.message?.let {
                Pair(it, 0)
            } ?: run {
                Pair(R.string.generic_error_message, 400)
            }
        }
    }

    private fun handleError(errorBody: ErrorBody): Pair<Any, Int> {
        return if (errorBody.code != ErrorBody.UNKNOWN_ERROR) {
            var errorMessage: String? = null
            errorBody.errors?.forEach {
                if (errorMessage == null) {
                    errorMessage = it
                } else {
                    errorMessage += "\n" + it
                }
            }

            errorMessage?.let {
                Pair(it, errorBody.code)
            } ?: run { Pair(R.string.generic_error_message, errorBody.code) }
        } else {
            Pair(R.string.generic_error_message, 400)
        }
    }

    private fun isNetworkError(throwable: Throwable): Boolean {
        return throwable is SocketException ||
                throwable is UnknownHostException ||
                throwable is SocketTimeoutException
    }
}
