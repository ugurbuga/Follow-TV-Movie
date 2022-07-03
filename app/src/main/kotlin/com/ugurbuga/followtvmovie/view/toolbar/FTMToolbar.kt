package com.ugurbuga.followtvmovie.view.toolbar

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import com.google.android.material.appbar.MaterialToolbar
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.core.extensions.color
import com.ugurbuga.followtvmovie.core.extensions.drawable
import com.ugurbuga.followtvmovie.core.extensions.font
import com.ugurbuga.followtvmovie.extensions.showKeyboard

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
                    logo = drawable(R.drawable.ic_ftm_app_logo)
                }
                NavigationIconType.BACK_BUTTON -> {
                    navigationIcon = drawable(R.drawable.ic_arrow_back)
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

    fun setSearchView(
        menuSearchItemId: Int,
        isExpand: Boolean = true,
        onQueryChanged: ((query: String) -> Unit)
    ) {
        val menuSearchItem = menu.findItem(menuSearchItemId)

        if (menuSearchItem != null && menuSearchItem.actionView is SearchView) {
            val searchView = menuSearchItem.actionView as SearchView

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    onQueryChanged.invoke(query)
                    return false
                }

                override fun onQueryTextChange(query: String): Boolean {
                    onQueryChanged.invoke(query)
                    return false
                }
            })

            val searchIcon: ImageView =
                searchView.findViewById(androidx.appcompat.R.id.search_button)

            searchIcon.setImageDrawable(drawable(R.drawable.ic_search))

            val backgroundView =
                searchView.findViewById(androidx.appcompat.R.id.search_plate) as View
            backgroundView.background = null

            val searchAutoComplete: SearchView.SearchAutoComplete =
                searchView.findViewById(androidx.appcompat.R.id.search_src_text)

            searchAutoComplete.setTextColor(color(R.color.primary_color))
            searchAutoComplete.typeface = font(R.font.league_spartan_regular)

            searchView.isIconified = !isExpand

            if (isExpand) {
                searchAutoComplete.post {
                    searchAutoComplete.requestFocus()
                    searchAutoComplete.showKeyboard()
                }
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
