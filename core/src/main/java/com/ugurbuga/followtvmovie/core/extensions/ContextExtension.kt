package com.ugurbuga.followtvmovie.core.extensions

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate

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