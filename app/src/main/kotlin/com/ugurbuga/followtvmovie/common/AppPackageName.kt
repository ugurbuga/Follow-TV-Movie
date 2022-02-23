package com.ugurbuga.followtvmovie.common

import androidx.annotation.StringDef

@StringDef(
    value = [
        AppPackageName.FACEBOOK,
        AppPackageName.INSTAGRAM,
        AppPackageName.TWITTER,
        AppPackageName.IMDB
    ]
)
annotation class AppPackageName {
    companion object {
        const val FACEBOOK = "com.facebook.katana"
        const val INSTAGRAM = "com.instagram.android"
        const val TWITTER = "com.twitter.android"
        const val IMDB = "com.imdb.mobile"
    }
}