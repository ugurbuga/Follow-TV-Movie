package com.ugurbuga.followtvmovie.view

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textview.MaterialTextView
import com.ugurbuga.followtvmovie.R

open class FTMTextView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = R.attr.ftmTextView
) : MaterialTextView(
    context, attributeSet, defStyleAttr
)
