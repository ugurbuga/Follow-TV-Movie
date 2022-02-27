package com.ugurbuga.followtvmovie.data.preferences

import android.content.Context

abstract class FTMSharedPreferences(context: Context) {

    abstract fun getPrefName(): String

    protected val prefs = context.getSharedPreferences(getPrefName(), Context.MODE_PRIVATE)




}