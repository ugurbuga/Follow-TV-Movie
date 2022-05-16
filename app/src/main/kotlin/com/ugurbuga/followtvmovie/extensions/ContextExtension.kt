package com.ugurbuga.followtvmovie.extensions

import android.content.Context
import android.content.pm.PackageManager

fun Context.isPackageEnabled(packageName: String): Boolean {
    return try {
        packageManager.getApplicationInfo(packageName, 0).enabled
    } catch (e: PackageManager.NameNotFoundException) {
        false
    }
}