package com.ugurbuga.followtvmovie.view

import android.content.Context
import android.util.AttributeSet
import android.webkit.WebView
import com.ugurbuga.followtvmovie.core.extensions.fixUiModeIfNeeded

open class FTMUiModeCareWebView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : WebView(context, attrs, defStyleAttr) {

    init {
        context.fixUiModeIfNeeded()
    }
}