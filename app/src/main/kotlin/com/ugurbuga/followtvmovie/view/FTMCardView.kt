package com.ugurbuga.followtvmovie.view

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.card.MaterialCardView
import com.ugurbuga.followtvmovie.R

class FTMCardView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = R.attr.ftmCardView
) : MaterialCardView(
    context, attributeSet, defStyleAttr
)