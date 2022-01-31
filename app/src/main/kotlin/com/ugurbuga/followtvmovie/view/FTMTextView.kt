package com.ugurbuga.followtvmovie.view

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import androidx.annotation.DimenRes
import androidx.appcompat.widget.AppCompatTextView
import com.ugurbuga.followtvmovie.R

class FTMTextView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = R.attr.ftmTextView
) : AppCompatTextView(
    context, attributeSet, defStyleAttr
) {
    @DimenRes
    var textSizeFromDimenRes = R.dimen.text_size_13
        set(value) {
            field = value
            setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(value)
            )
        }
}
