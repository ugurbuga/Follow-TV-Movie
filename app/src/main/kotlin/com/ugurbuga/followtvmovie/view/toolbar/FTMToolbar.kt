package com.ugurbuga.followtvmovie.view.toolbar

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.google.android.material.appbar.MaterialToolbar
import com.ugurbuga.followtvmovie.R

class FTMToolbar @JvmOverloads constructor(
    context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = R.attr.ftmToolbar
) : MaterialToolbar(
    context, attributeSet, defStyleAttr
) {

    private var navigationIconType = NavigationIconType.NONE
        set(value) {
            field = value
            when (value) {
                NavigationIconType.LOGO -> {
                    navigationIcon = null
                    logo = ContextCompat.getDrawable(context, R.drawable.ic_ftm_app_logo)
                }
                NavigationIconType.BACK_BUTTON -> {
                    navigationIcon = ContextCompat.getDrawable(context, R.drawable.ic_arrow_back)
                    logo = null
                }
                NavigationIconType.NONE -> {
                    navigationIcon = null
                    logo = null
                }
            }
        }

    init {
        attributeSet?.let {
            val attrs = context.obtainStyledAttributes(it, R.styleable.FTMToolbar)
            try {
                navigationIconType =
                    NavigationIconType.of(attrs.getInt(R.styleable.FTMToolbar_navigation_type, 0))
            } finally {
                attrs.recycle()
            }
        }
    }

    fun setNavigationClickListener(listener: (() -> Unit)) {
        setNavigationOnClickListener {
            if (navigationIconType == NavigationIconType.BACK_BUTTON) {
                listener.invoke()
            }
        }
    }

    enum class NavigationIconType(private val value: Int) {
        NONE(value = 0), LOGO(value = 1), BACK_BUTTON(value = 2);

        companion object {
            fun of(value: Int): NavigationIconType =
                values().firstOrNull { it.value == value } ?: NONE
        }
    }
}
