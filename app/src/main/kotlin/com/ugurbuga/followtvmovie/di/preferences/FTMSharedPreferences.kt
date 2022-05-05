package com.ugurbuga.followtvmovie.di.preferences

import android.content.Context

abstract class FTMSharedPreferences(context: Context) {

    abstract fun getPrefName(): String

    protected val prefs = context.getSharedPreferences(getPrefName(), Context.MODE_PRIVATE)




}