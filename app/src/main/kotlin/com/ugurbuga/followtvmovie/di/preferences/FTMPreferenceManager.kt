package com.ugurbuga.followtvmovie.di.preferences

import android.content.Context
import com.ugurbuga.followtvmovie.extensions.get
import com.ugurbuga.followtvmovie.extensions.set
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FTMPreferenceManager @Inject constructor(
    @ApplicationContext private val context: Context
) : FTMSharedPreferences(context), Preferences {

    companion object {
        const val KEY_THEME = "theme"

    }

    override fun getPrefName() = "FTMPref"

    override fun getTheme(): Int {
        return prefs.get<Int>(KEY_THEME)?.toInt() ?: -1
    }

    override fun setTheme(theme: Int) {
        prefs.set(KEY_THEME, theme)
    }
}
