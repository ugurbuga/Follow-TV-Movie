package com.ugurbuga.followtvmovie.view.toolbar

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.R
import com.google.android.material.appbar.CollapsingToolbarLayout

class FTMCollapsingToolbarLayout @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = R.attr.collapsingToolbarLayoutStyle
) : CollapsingToolbarLayout(
    context, attributeSet, defStyleAttr
) {
    init {
        setCollapsedTitleTypeface(
            ResourcesCompat.getFont(
                context, com.ugurbuga.followtvmovie.R.font.sofiapro_semibold
            )
        )
        setExpandedTitleTypeface(
            ResourcesCompat.getFont(
                context, com.ugurbuga.followtvmovie.R.font.sofiapro_semibold
            )
        )
    }
}