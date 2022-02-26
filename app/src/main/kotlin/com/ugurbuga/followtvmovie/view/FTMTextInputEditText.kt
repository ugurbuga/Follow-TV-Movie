package com.ugurbuga.followtvmovie.view

import android.content.Context
import android.util.AttributeSet
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import com.google.android.material.textfield.TextInputEditText
import com.ugurbuga.followtvmovie.R

open class FTMTextInputEditText @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = R.attr.ftmTextInputEditText
) : TextInputEditText(
    context, attributeSet, defStyleAttr
) {

    init {
        onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                if (parent is ViewGroup && parent.parent is FTMTextInputLayout)
                    (parent.parent as FTMTextInputLayout).errorState = null
            }
        }
    }

    fun removeFocus() {
        isEnabled = !isEnabled
        isEnabled = !isEnabled
    }
}
