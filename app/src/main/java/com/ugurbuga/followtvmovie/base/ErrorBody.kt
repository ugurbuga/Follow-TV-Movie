package com.ugurbuga.followtvmovie.base

import android.os.Parcelable
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import okhttp3.ResponseBody
import retrofit2.Response
import java.nio.charset.Charset

@Parcelize
data class ErrorBody(
    var code: Int,
    @SerializedName("errors") val errors: List<ErrorResponse>? = null
) : Parcelable {

    companion object {
        const val UNKNOWN_ERROR = 0
        private val gson = Gson()
        fun parseError(response: Response<*>?): ErrorBody? {
            return response?.let { res ->
                res.errorBody()?.let {
                    try {
                        val error = cloneBuffer(it)
                        var body: ErrorBody? = gson.fromJson(error, ErrorBody::class.java)
                        if (body == null) {
                            body = ErrorBody(res.code())
                        } else {
                            body.code = res.code()
                        }
                        body
                    } catch (ignored: Exception) {
                        ErrorBody(res.code())
                    }
                }
            }
        }

        private fun cloneBuffer(body: ResponseBody): String {
            val source = body.source()
            source.request(Long.MAX_VALUE)
            val buffer = source.buffer
            return buffer.clone().readString(Charset.forName("UTF-8"))
        }
    }
}
