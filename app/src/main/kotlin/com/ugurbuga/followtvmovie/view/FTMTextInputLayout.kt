package com.ugurbuga.followtvmovie.view

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputLayout
import com.ugurbuga.followtvmovie.R

open class FTMTextInputLayout @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = R.attr.ftmTextInputLayout
) : TextInputLayout(
    context, attributeSet, defStyleAttr
) {
    var errorState: String?
        get() = error.toString()
        set(value) {
            isErrorEnabled = value.isNullOrEmpty().not()
            error = value
        }
}
