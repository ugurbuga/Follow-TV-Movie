package com.ugurbuga.followtvmovie.base

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ErrorResponse(
    @SerializedName("message") val message: String,
    @SerializedName("reason") val reason: String,
    @SerializedName("subject") val subject: String,
    @SerializedName("subjectType") val subjectType: String,
    @SerializedName("type") val type: ErrorType?,
) : Parcelable

enum class ErrorType {
    @SerializedName("ValidationError")
    VALIDATION_ERROR;

    companion object {
        fun getValue(value: ErrorType) = when (value) {
            VALIDATION_ERROR -> "ValidationError"
        }
    }
}
