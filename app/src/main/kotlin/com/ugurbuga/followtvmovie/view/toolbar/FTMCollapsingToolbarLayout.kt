package com.ugurbuga.followtvmovie.view.toolbar

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.ugurbuga.followtvmovie.R

class FTMCollapsingToolbarLayout @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = com.google.android.material.R.attr.collapsingToolbarLayoutStyle
) : CollapsingToolbarLayout(
    context, attributeSet, defStyleAttr
) {
    init {
        setCollapsedTitleTypeface(
            ResourcesCompat.getFont(
                context, R.font.sofiapro_semibold
            )
        )
        setExpandedTitleTypeface(
            ResourcesCompat.getFont(
                context, R.font.sofiapro_semibold
            )
        )
    }
}