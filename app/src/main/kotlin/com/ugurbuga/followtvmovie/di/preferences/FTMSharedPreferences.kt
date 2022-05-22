package com.ugurbuga.followtvmovie.di.preferences

import android.content.Context
import android.content.SharedPreferences

abstract class FTMSharedPreferences(context: Context) {

    abstract fun getPrefName(): String

    protected val prefs: SharedPreferences = context.getSharedPreferences(getPrefName(), Context.MODE_PRIVATE)




}