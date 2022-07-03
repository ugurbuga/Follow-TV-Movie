package com.ugurbuga.followtvmovie.core.extensions

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

fun Context.fixUiModeIfNeeded() {
    val configuration = resources.configuration
    val configurationNighMode = configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
    val appCompatNightMode = AppCompatDelegate.getDefaultNightMode()

    val newUiModeConfiguration = when {
        configurationNighMode == Configuration.UI_MODE_NIGHT_NO && appCompatNightMode == AppCompatDelegate.MODE_NIGHT_YES -> {
            Configuration.UI_MODE_NIGHT_YES or (configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK.inv())
        }
        configurationNighMode == Configuration.UI_MODE_NIGHT_YES && appCompatNightMode == AppCompatDelegate.MODE_NIGHT_NO -> {
            Configuration.UI_MODE_NIGHT_NO or (configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK.inv())
        }
        else -> null
    }

    if (newUiModeConfiguration != null) {
        val fixedConfiguration = Configuration().apply {
            uiMode = newUiModeConfiguration
        }
        @Suppress("DEPRECATION")
        resources.updateConfiguration(
            fixedConfiguration,
            resources.displayMetrics
        )
    }
}

fun Context.dimenToPx(@DimenRes dimen: Int): Int {
    return resources.dimenToPx(dimen)
}

fun Context.font(@FontRes resource: Int): Typeface? {
    return ResourcesCompat.getFont(this, resource)
}

fun Context.color(@ColorRes resource: Int): Int {
    return ContextCompat.getColor(this, resource)
}

fun Context.colorStateList(@ColorRes resource: Int): ColorStateList? {
    return ContextCompat.getColorStateList(this, resource)
}

fun Context.drawable(@DrawableRes resource: Int): Drawable? {
    return ContextCompat.getDrawable(this, resource)
}