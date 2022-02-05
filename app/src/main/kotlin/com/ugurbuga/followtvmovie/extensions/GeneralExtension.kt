package com.ugurbuga.followtvmovie.extensions

fun <T> lazyThreadSafetyNone(initializer: () -> T):
        Lazy<T> = lazy(LazyThreadSafetyMode.NONE, initializer)
